package ru.grig.charm.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChBackRunner {
    public static void main(String[] args) throws IOException, InterruptedException {

        try(HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/profile?id=1"))
                    .GET()
                    .build();


            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
            HttpClient.Version version = response.version();
            int statusCode = response.statusCode();
            String headers = response.headers().map().entrySet().stream().map(Object::toString).collect(Collectors.joining("\n"));
            String body = new String(response.body());
            System.out.println(version + " " + statusCode + "\n" + headers + "\n\n" + body);

        }

    }
}