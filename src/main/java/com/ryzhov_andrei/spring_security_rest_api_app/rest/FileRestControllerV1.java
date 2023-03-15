package com.ryzhov_andrei.spring_security_rest_api_app.rest;

import com.ryzhov_andrei.spring_security_rest_api_app.dto.FileDto;
import com.ryzhov_andrei.spring_security_rest_api_app.dto.UserDto;
import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.model.User;
import com.ryzhov_andrei.spring_security_rest_api_app.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/files/")
public class FileRestControllerV1 {

    private  final FileService fileService;

    public FileRestControllerV1(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getFileById(@PathVariable Long id) {
        File file = fileService.getById(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        FileDto resultFile = FileDto.fromFile(file);
        return new ResponseEntity<>(resultFile, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<FileDto> getFileByName(@PathVariable String name) {
        File file = fileService.findByFilename(name);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        FileDto resultFile = FileDto.fromFile(file);
        return new ResponseEntity<>(resultFile, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getAll() {
        List<File> files = fileService.getAll();
        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<FileDto> fileDtoList = FileDto.toFileDtos(files);
        return new ResponseEntity(fileDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        File file = this.fileService.getById(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        fileService.deleteById(id);
        return new ResponseEntity(file,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FileDto> upload(@RequestBody @NonNull FileDto fileDto) {
        fileService.uploadFile(fileDto.toFile());
        return new ResponseEntity("File Uploaded Successfully",HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> download(@RequestBody @NonNull FileDto fileDto) {
        if (StringUtils.hasText(fileDto.getFilename())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is missing");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                fileDto.getFilename());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource = new InputStreamResource(fileService.download(fileDto.toFile()));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//                ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(resource);
    }
}
