package com.mangokiwi.config;

import com.mangokiwi.service.storage.FileSystemStorageService;
import com.mangokiwi.service.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by maolei on 5/24/17.
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Bean
    public CommandLineRunner setupInitialEnv(){
        return (args) -> {
            fileSystemStorageService.deleteAll();
            fileSystemStorageService.init();
        };
    }
}
