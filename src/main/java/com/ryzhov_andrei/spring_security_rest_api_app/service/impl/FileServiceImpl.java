package com.ryzhov_andrei.spring_security_rest_api_app.service.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;

import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.properties.S3Properties;
import com.ryzhov_andrei.spring_security_rest_api_app.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    @Autowired
    public FileServiceImpl(AmazonS3 amazonS3, S3Properties s3Properties) {
        this.amazonS3 = amazonS3;
        this.s3Properties = s3Properties;
    }


    @Override
    public void upload(File file) {

        java.io.File newFile = new java.io.File(file.getLocation());
        file.setCreated(LocalDateTime.now());
        log.info("File Uploaded {}", file.getFileName());

        try{
            amazonS3.putObject(s3Properties.getBucketName(), file.getFileName(), newFile);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()),
                    "Request processing failed at cloud platform", e);
        } catch (SdkClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to process your request", e);
        }
    }


    @Override
    public InputStream download(File file) {

        if (amazonS3.doesObjectExist(s3Properties.getBucketName(), file.getFileName())) {
            S3Object s3Object = amazonS3.getObject(s3Properties.getBucketName(), file.getFileName());
            log.info("File download {}", file.getFileName());
            return s3Object.getObjectContent();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Requested file does not exist on bucket");
        }
    }


    @Override
    public Optional<String> listFiles() {
        ObjectListing objectListing = amazonS3.listObjects(s3Properties.getBucketName());

        if (objectListing != null) {
            log.info("All files in the bucket");
            return Optional.of(String.valueOf(objectListing.getObjectSummaries()));
        } else {
            log.info("No file present in bucket");
            return Optional.empty();
        }
    }


    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(s3Properties.getBucketName(), fileName);
        log.info("Deleting a File {}", fileName);
    }

    @Override
    public File findByFileName(String name) {
        S3Object s3Object = amazonS3.getObject(s3Properties.getBucketName(), file.getFileName());
        return null;
    }

    @Override
    public File findById(Long id) {
        return null;
    }
}
