package com.example.project.user.model.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
