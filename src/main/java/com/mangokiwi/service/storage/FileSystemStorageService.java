package com.mangokiwi.service.storage;

import com.mangokiwi.core.exception.StorageException;
import com.mangokiwi.core.exception.StorageFileExceedSizeLimitException;
import com.mangokiwi.core.exception.StorageFileInvalidTypeException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
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

    public void createUserDir(Long userId, Path path) {
        try {
            Path userPath = path.resolve(userId.toString());
            if (Files.exists(userPath))
                FileSystemUtils.deleteRecursively(userPath.toFile());
            Files.createDirectory(path.resolve(userId.toString()));
        } catch (IOException e) {
            throw new StorageException("Could not create user's directory");
        }
    }

    public void store(MultipartFile file, Long userId, String fileType) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            switch (fileType) {
                case "diploma":
                    createUserDir(userId, this.pathTree.get("diplomaLocation"));
                    Files.copy(file.getInputStream(), this.pathTree.get("diplomaLocation").resolve(userId.toString()).resolve(file.getOriginalFilename()));
                    break;
                case "resume":
                    createUserDir(userId, this.pathTree.get("resumeLocation"));
                    Files.copy(file.getInputStream(), this.pathTree.get("resumeLocation").resolve(userId.toString()).resolve(file.getOriginalFilename()));
                    break;
                default:
                    throw new StorageFileInvalidTypeException("Unreconigzed type of file");
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
