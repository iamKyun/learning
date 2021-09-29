package com.iamkyun.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
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
        return filePartFlux.doOnNext(this::uploadToMinio).then(Mono.just("OK"));
    }

    private void uploadToMinio(FilePart filePart) {
        Path dest = Paths.get(filePart.filename());
        filePart.transferTo(dest).doOnSuccess(v -> {
            try {
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("demo").build());
                if (!found) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket("demo").build());
                }

                ObjectWriteResponse res = minioClient.uploadObject(
                        UploadObjectArgs.builder()
                                        .bucket("demo")
                                        .object(dest.getFileName().toString())
                                        .filename(dest.toAbsolutePath().toString())
                                        .build());
                System.out.println(res.etag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).subscribe((v) -> System.out.println("fin."));

    }


}
