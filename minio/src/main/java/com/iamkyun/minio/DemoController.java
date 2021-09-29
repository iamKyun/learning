package com.iamkyun.minio;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/upload")
    public Mono<String> upload(@RequestPart("files") Flux<FilePart> filePartFlux) {
        return filePartFlux.doOnNext(this::uploadToMinio).then(Mono.just("OK"));
    }

    private void uploadToMinio(FilePart filePart) {

    }


}
