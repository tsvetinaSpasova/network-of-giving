package com.vmware.backend.controller;


import com.vmware.backend.service.impl.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("image")
public class FileUploadController {

    @Autowired
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    //@GetMapping("/{filename:.+}")
    @GetMapping(
            value = "/{filename:.+}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] getFile(@PathVariable String filename) throws MalformedURLException {
        return fileUploadService.findImage(filename);
    }

    @PostMapping
    @ResponseBody
    public String createFile (@RequestParam("file") MultipartFile file){
        try {
            return this.fileUploadService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping
    @ResponseBody
    public void deleteFile (@RequestParam String filename){
        try {
            this.fileUploadService.delete(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
