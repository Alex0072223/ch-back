package ru.grig.charm.back;

import ru.grig.charm.back.controller.ProfileController;
import ru.grig.charm.back.dao.ProfileDao;
import ru.grig.charm.back.model.Profile;
import ru.grig.charm.back.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));


        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
        ) {
            String request = requestStream.readUTF();

            String response;
            while (!"stop".equals(request)){
                if (request.startsWith("save ")){
                    response = controller.save(request.split("save ")[1]);
                } else {
                    response = "unsup operation";
                }


                responseStream.writeUTF(response);
                request = requestStream.readUTF();
            }

        }
    }
}
