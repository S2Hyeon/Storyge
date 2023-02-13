package com.example.project.user.model.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    //    FileDto upload(Long userId, UserUpdateParam param, String dirName) throws IOException;
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
