package com.example.project.user.model.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class FileDto {
    private String fileName;
    private String url;

    public FileDto(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }
}
