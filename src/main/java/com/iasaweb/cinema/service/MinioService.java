package com.iasaweb.cinema.service;

import com.iasaweb.cinema.dto.MovieDto;
import com.iasaweb.cinema.exception.MovieImageNotFound;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.errors.ErrorResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class MinioService {
    private final MinioClient minioClient;
    private static final String MOVIE_IMAGES_BUCKET = "movie-images";

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadMovieImage(MultipartFile file) throws IOException, MinioException {
        var args = PutObjectArgs.builder()
                .bucket(MOVIE_IMAGES_BUCKET)
                .object(file.getOriginalFilename())
                .data(file.getBytes(), file.getBytes().length)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
    }

//    public String getTemporaryMovieImageUrl(String imageUrl) throws MinioException {
//        var args = GetPresignedObjectUrlArgs.builder()
//                        .method(Http.Method.GET)
//                        .bucket(MOVIE_IMAGES_BUCKET)
//                        .object(imageUrl)
//                        .expiry(15, TimeUnit.MINUTES)
//                        .extraQueryParams(Map.of("host", "localhost:9000"))
//                        .build();
//        return minioClient.getPresignedObjectUrl(args);
//    }

    public StatObjectResponse getMovieImageMetadata(MovieDto movie) throws MinioException {
        var args = StatObjectArgs.builder()
                .bucket(MOVIE_IMAGES_BUCKET)
                .object(movie.imageUrl())
                .build();
        try {
            return minioClient.statObject(args);
        } catch (ErrorResponseException e) {
            throw new MovieImageNotFound(movie.title());
        }
    }

    public InputStream getMovieImage(String imageUrl) throws MinioException {
        var args = GetObjectArgs.builder()
                .bucket(MOVIE_IMAGES_BUCKET)
                .object(imageUrl)
                .build();
        return minioClient.getObject(args);
    }
}
