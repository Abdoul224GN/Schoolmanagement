package org.school.service;

import jakarta.annotation.PostConstruct;
import org.school.config.FileStorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadRoot;
    private final String baseUrl;
    private final long maxSize;
    private final List<String> allowedTypes;

    public FileStorageService(FileStorageProperties props) {
        this.uploadRoot = Paths.get(props.getUploadDir()).toAbsolutePath().normalize();
        this.baseUrl = props.getBaseUrl().endsWith("/")
                ? props.getBaseUrl() : props.getBaseUrl() + "/";
        this.maxSize = props.getMaxSize();
        this.allowedTypes = props.getAllowedTypes();
    }

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(uploadRoot);
    }

    public String storeAndGetUrl(MultipartFile file) throws IOException {
        validateSize(file);
        validateType(file);

        String original = StringUtils.cleanPath(file.getOriginalFilename());
        if (original.contains("..")) {
            throw new SecurityException("Nom de fichier invalide : " + original);
        }

        String ext = "";
        int dot = original.lastIndexOf('.');
        if (dot >= 0) {
            ext = original.substring(dot);
        }

        String filename = UUID.randomUUID() + ext;
        Path target = uploadRoot.resolve(filename).normalize();

        if (!target.getParent().equals(uploadRoot)) {
            throw new SecurityException("Path traversal détecté");
        }

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    private void validateSize(MultipartFile file) {
        if (file.getSize() > maxSize) {
            throw new RuntimeException(
                    "Taille dépassée : " + file.getSize() + " > " + maxSize
            );
        }
    }

    private void validateType(MultipartFile file) {
        String mime = file.getContentType();
        if (mime == null || !allowedTypes.contains(mime)) {
            throw new RuntimeException("Type MIME non autorisé : " + mime);
        }
    }
}