package com.iamkyun.minio;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

class MinioApplicationTests {

    @Test
    void testFlux() {
        Flux.just("A", "B", "C").doOnNext((s) -> {
                System.out.println(s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).then(Mono.just("OK"))
            .subscribe(System.out::println);
    }

    @Test
    void testPath() {
        Path dest = Paths.get("test.png");
        System.out.println(dest.getFileName());
        System.out.println(dest.toAbsolutePath());
    }

}
