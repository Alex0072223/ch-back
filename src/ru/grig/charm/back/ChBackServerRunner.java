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

import static ru.grig.charm.back.model.Commands.*;

public class ChBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));


        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
        ) {
            String request = requestStream.readUTF();


            while (!"stop".equals(request)){
                String response;
                if (request.startsWith(SAVE.getPrefix())){
                    response = controller.save(request.split(SAVE.getPrefix())[1]);
                } else if (request.startsWith(FIND_BY_ID.getPrefix())){
                    response = controller.save(request.split(FIND_BY_ID.getPrefix())[1]);
                } else if (request.startsWith(FIND_ALL.getPrefix())){
                    response = controller.save(request.split(FIND_ALL.getPrefix())[1]);
                } else if (request.startsWith(UPDATE.getPrefix())){
                    response = controller.save(request.split(UPDATE.getPrefix())[1]);
                } else if (request.startsWith(DELETE.getPrefix())){
                    response = controller.save(request.split(DELETE.getPrefix())[1]);
                }
                else {
                    response = "unsup operation";
                }

                System.out.println("Client req: " + request);
                responseStream.writeUTF(response);
                request = requestStream.readUTF();
            }

        }
    }
}
