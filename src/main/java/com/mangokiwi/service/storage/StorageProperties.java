package com.mangokiwi.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

/**
 * Created by maolei on 5/24/17.
 */
@ConfigurationProperties()
public class StorageProperties {

    private LinkedHashMap<String, Path> dirMap;

    public StorageProperties() {
        dirMap = new LinkedHashMap<>();
        dirMap.put("storageLocation", Paths.get("./target/storage"));
        dirMap.put("resumeLocation", Paths.get("./target/storage/resume"));
        dirMap.put("diplomaLocation", Paths.get("./target/storage/diploma"));
    }

    public LinkedHashMap<String, Path> getDirMap() {
        return dirMap;
    }

    public void setDirMap(LinkedHashMap<String, Path> dirMap) {
        this.dirMap = dirMap;
    }
}
