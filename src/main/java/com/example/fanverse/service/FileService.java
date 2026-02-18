package com.example.fanverse.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private final Path dir;

  public FileService(@Value("${app.upload-dir}") String uploadDir) {
    this.dir = Paths.get(uploadDir);
  }

  public String save(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) return null;

    Files.createDirectories(dir);

    String original = file.getOriginalFilename();
    String ext = (original != null && original.contains("."))
        ? original.substring(original.lastIndexOf('.'))
        : "";

    String savedName = UUID.randomUUID() + ext;
    Path target = dir.resolve(savedName);
    file.transferTo(target.toFile());

    return "/uploads/" + savedName;
  }



}
