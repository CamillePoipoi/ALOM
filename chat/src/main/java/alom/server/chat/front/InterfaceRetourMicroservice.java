package alom.server.chat.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class InterfaceRetourMicroservice {

    // Simule une base de données pour stocker les informations d'authentification
    private static final Map<String, String> authDatabase = new HashMap<>();

    public static void main(String[] args) {
        
        SpringApplication.run(InterfaceRetourMicroservice.class, args);

        // Démarrage du serveur TCP
        startTCPServer();
    }

    private static void startTCPServer() {
        try {
            // Port sur lequel le serveur écoute les connexions
            int port = 8081;
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Serveur TCP démarré sur le port " + port);

            while (true) {
                // Attend une connexion entrante
                Socket clientSocket = serverSocket.accept();

                // Gère la connexion dans un thread séparé
                new Thread(() -> handleConnection(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket clientSocket) {
        try {
            // Lecture du token depuis la connexion
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String token = reader.readLine();

            // Obtient le nickname associé au token depuis la "base de données"
            String nickname = authDatabase.get(token);

            // Répond au client
            clientSocket.getOutputStream().write(("Nickname associé au token : " + nickname).getBytes());

            // Ferme la connexion
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
