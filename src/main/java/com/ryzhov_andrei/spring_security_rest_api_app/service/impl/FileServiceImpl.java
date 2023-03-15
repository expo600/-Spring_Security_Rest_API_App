package com.ryzhov_andrei.spring_security_rest_api_app.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Status;
import com.ryzhov_andrei.spring_security_rest_api_app.repository.FileRepository;
import com.ryzhov_andrei.spring_security_rest_api_app.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
@Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getById(Long id) {
        File file = fileRepository.findById(id).orElse(null);
        if (file == null) {
            log.warn("FindById - no File find by id: {}", id);
        }
        log.info("Find File by id: {}", id);
        return file;
    }

    @Override
    public File findByFilename(String filename) {
       File file = fileRepository.findByFilename(filename);
        if (file == null) {
            log.warn("FindByName - no File find by name: {}",filename);
        }
        log.info("Find User by name: {}", filename);
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> files = fileRepository.findAll();
        log.info("GetAll:  {} Files found", files.size());
        return files;
    }


    @Override
    public void deleteById(Long id) {
        File file = fileRepository.findById(id).orElse(null);
        if (file == null) {
            log.warn("DeleteById - no File find by id: {}", id);
        } else {
            log.info("Delete File by id: {}", id);
            file.setStatus(Status.DELETED);
            fileRepository.save(file);
        }
    }

    @Override
    public File uploadFile(File file) {
//        java.io.File newFile = new java.io.File(file.getLocation());
//        file.setTimeOfCreating(LocalDateTime.now());
//        log.info("File Uploaded {}", file.getFileName());
//
//        try {
//            amazonS3.putObject(s3Properties.getBucketName(), file.getFileName(), newFile);
//        } catch (AmazonServiceException e) {
//            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()),
//                    "Request processing failed at cloud platform", e);
//        } catch (SdkClientException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process your request", e);
//        }
        return null;
    }

    @Override
    public InputStream download(File file) {
//        if (amazonS3.doesObjectExist(s3Properties.getBucketName(), file.getFileName())) {
//            S3Object s3Object = amazonS3.getObject(s3Properties.getBucketName(), file.getFileName());
//            log.info("File download {}", file.getFileName());
//            return s3Object.getObjectContent();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested file does not exist on bucket");
//        }
        return null;
    }

}
