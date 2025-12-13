package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadDir;

    public FileStorageService(@Value("${app.upload.dir}") String uploadDirStr) throws IOException {
        this.uploadDir = Paths.get(uploadDirStr).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir);
    }

    public String storeFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        // Validate content type
        String contentType = file.getContentType();
        if (contentType == null || !(contentType.startsWith("image/"))) {
            throw new IOException("Only image files are allowed");
        }

        // Limit check (optional: you already set spring.servlet.multipart.*)
        long maxBytes = 5 * 1024 * 1024; // 5MB
        if (file.getSize() > maxBytes) throw new IOException("File too large");


        String ext = getExtension(file.getOriginalFilename());
        String filename = UUID.randomUUID().toString() + (ext.isEmpty() ? "" : "." + ext);

        Path target = this.uploadDir.resolve(filename);

        if (!target.normalize().startsWith(this.uploadDir)) {
            throw new IOException("Cannot store file outside upload directory.");
        }

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private String getExtension(String filename) {
        if (filename == null) return "";
        int i = filename.lastIndexOf('.');
        return (i >= 0) ? filename.substring(i + 1) : "";
    }

    public Path load(String filename) {
        return uploadDir.resolve(filename).normalize();
    }
}
