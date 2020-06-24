package com.vmware.backend.service.impl;

import com.vmware.backend.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO exceptions
@Service
public class FileUploadService implements IFileUploadService {
    @Value("${images.path}")
    private String imagesPath;


    @Override
    public String upload(MultipartFile file) throws IOException {
        String name =  file.getOriginalFilename();
        if(Paths.get(imagesPath, name).toFile().exists()){
            return name;
        }
        if(!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(imagesPath, name));
        }
        return name;
    }

    @Override
    public void delete(String fileName) throws IOException {
      Files.deleteIfExists(Paths.get(imagesPath, fileName));
    }

    @Override
    public byte[] findImage(String fileName) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(imagesPath + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



//    @Override
//    public Resource findImage(String fileName) {
//        return (Resource) resourceLoader.getResource("file:" + imagesPath + "/" + fileName);
//    }
}
