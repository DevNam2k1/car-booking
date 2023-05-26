package com.project.BookingCar.service.impl;

import com.project.BookingCar.config.FileStorageProperties;
import com.project.BookingCar.exceptions.FileStorageException;
import com.project.BookingCar.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private Path fileStorageLocation;

    public FileStorageServiceImpl() {
        // TODO document why this constructor is empty
    }

    @Autowired
    public void FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("this.translator.toLocale(MessageUtils.FILE_NOT_CREATE");
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains(".."))
                throw new FileStorageException("this.translator.toLocale(MessageUtils.FILE_CONTAINS_INVALID)");

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "booking/files/" + fileName;
        } catch (IOException ex) {
            throw new FileStorageException("this.translator.toLocale(MessageUtils.FILE_NOT_STORE, fileName");
        }
    }
}
