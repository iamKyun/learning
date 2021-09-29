package com.iamkyun.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/demo")
public class DemoController {

    final MinioClient minioClient =
            MinioClient.builder()
                       .endpoint("http://172.26.188.113:9000/")
                       .credentials("demouser", "demouser")
                       .build();

    @PostMapping("/upload")
    public Mono<String> upload(@RequestPart("files") Flux<FilePart> filePartFlux) {
        return filePartFlux.doOnNext((item) -> {
            try {
                uploadToMinio(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).then(Mono.just("OK"));
    }

    private void uploadToMinio(FilePart filePart) throws Exception {
        Path dest = Paths.get(filePart.filename());
        filePart.transferTo(dest);

        boolean found =
                minioClient.bucketExists(BucketExistsArgs.builder().bucket("demo").build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("demo").build());
        } else {
            System.out.println("Bucket 'demo' already exists.");
        }

        minioClient.uploadObject(
                UploadObjectArgs.builder()
                                .bucket("demo")
                                .object(dest.getFileName().toString())
                                .filename(dest.toAbsolutePath().toString())
                                .build());
    }


}
