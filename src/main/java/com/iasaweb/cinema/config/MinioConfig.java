package com.iasaweb.cinema.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class MinioConfig {
    private final String minioRootUser;
    private final String minioRootPassword;

    public MinioConfig(
        @Value("${minio.root-user}") String minioRootUser,
        @Value("${minio.root-password}") String minioRootPassword
    ) {
        this.minioRootUser = minioRootUser;
        this.minioRootPassword = minioRootPassword;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://minio:9000")
                .credentials(minioRootUser, minioRootPassword)
                .build();
    }
}
