package com.neuromed.reports.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

  private final Path storageLocation = Paths.get("uploads");

  public FileStorageService() throws Exception {
    Files.createDirectories(storageLocation);
  }

  public String storeFile(MultipartFile file) {
    try {
      String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      Path targetLocation = storageLocation.resolve(filename);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return "/uploads/" + filename;
    } catch (Exception e) {
      throw new RuntimeException("Failed to store file", e);
    }
  }
}
