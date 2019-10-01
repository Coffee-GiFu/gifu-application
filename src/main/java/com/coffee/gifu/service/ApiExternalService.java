package com.coffee.gifu.service;

import javassist.NotFoundException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiExternalService {

    public String getUrlBody(String url) throws IOException, InterruptedException, NotFoundException {
        HttpResponse<String> response;
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();


        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 404){
            throw new NotFoundException("The server responded with a status of 404.");
        }
        return response.body();
    }
}
