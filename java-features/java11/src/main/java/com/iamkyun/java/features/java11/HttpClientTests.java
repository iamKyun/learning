package com.iamkyun.java.features.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientTests {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                                          .version(HttpClient.Version.HTTP_2)
                                          .connectTimeout(Duration.ofSeconds(20))
                                          .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                             .GET()
                                             .uri(URI.create("http://localhost"))
                                             .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
