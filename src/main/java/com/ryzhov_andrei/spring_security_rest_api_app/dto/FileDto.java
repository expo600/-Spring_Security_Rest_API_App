package com.ryzhov_andrei.spring_security_rest_api_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Event;
import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Status;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {
    private Long id;
    private String filename;
    private String location;


    public File toFile() {
        File file = new File();
        file.setId(id);
        file.setFilename(filename);
        file.setLocation(location);
        return file;
    }

    public static FileDto fromFile(File file) {
        if (Objects.isNull(file)) {
            return null;
        }
        FileDto fileDto = new FileDto();
        fileDto.setId(file.getId());
        fileDto.setFilename(file.getFilename());
        fileDto.setLocation(file.getLocation());
        return fileDto;
    }
    public static List<FileDto> toFileDtos(List<File> files) {
        List<FileDto> fileDtoList = new ArrayList<>();
        for (File file: files) {
            fileDtoList.add(fromFile(file));
        }
        return fileDtoList;
    }
}
