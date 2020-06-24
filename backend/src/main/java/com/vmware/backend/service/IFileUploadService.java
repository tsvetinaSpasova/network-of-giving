package com.vmware.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IFileUploadService {
    String upload(MultipartFile file) throws IOException;
    void delete(String fileName) throws IOException;
    byte[] findImage(String fileName) throws MalformedURLException;

}
