package com.ryzhov_andrei.spring_security_rest_api_app.service;

import com.ryzhov_andrei.spring_security_rest_api_app.model.File;

import java.io.InputStream;

public interface FileService extends GenericService<File,Long>{
    File uploadFile(File file);
    InputStream download (File file);
    File findByFilename(String filename);

}
