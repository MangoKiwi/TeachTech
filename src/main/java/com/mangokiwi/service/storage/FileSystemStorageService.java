package com.mangokiwi.service.storage;

import com.mangokiwi.core.exception.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;


/**
 * Created by maolei on 5/23/17.
 */
@Service
public class FileSystemStorageService {

    private final LinkedHashMap<String, Path> pathTree;

    public FileSystemStorageService(StorageProperties properties) {
        pathTree = properties.getDirMap();
    }

    public void init() {
        try {
            for (Path path : pathTree.values()){
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    public void store(MultipartFile file, String fileType) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            switch (fileType) {
                case "diploma":
                    Files.copy(file.getInputStream(), this.pathTree.get("diplomaLocation").resolve(file.getOriginalFilename()));
                    break;
                case "resume":
                    Files.copy(file.getInputStream(), this.pathTree.get("resumeLocation").resolve(file.getOriginalFilename()));
                    break;
                default:
                    throw new StorageException("Unreconigzed type of file");
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public void deleteAll() {
        for (Path path : pathTree.values()){
            FileSystemUtils.deleteRecursively(path.toFile());
        }
    }
}
