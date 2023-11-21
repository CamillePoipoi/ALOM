package fr.alom.retour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetourApplication {

    static HashMap<String, ConnexionClient> connexions = new HashMap<String, ConnexionClient>();

    public static void main(String[] args) {
        SpringApplication.run(RetourApplication.class, args);
    }

    public static void startTCPServer(){
        try {
            int port = 8080;
            boolean runnable = true;

            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Serveur TCP démarré sur le port " + port);

            while (runnable) {
                Socket clientSocket = serverSocket.accept();

				ConnexionClient connexion = new ConnexionClient(clientSocket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleMessage(String nickname, String message, String canal){
        // Un microservice nous envoie : nickname, message, emetteur.

        // Récupération du thread du récepteur
        Thread receiverThread = threads.get(nickname);

		receiverThread.run();

		
        // Envoyer les informations à un certain client sur le thread qui lui est associé
        // ...
    }

    private static void handleConnection(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Lire le token et déterminer le surnom associé
            String token = reader.readLine();
            String nickname = determineNicknameFromToken(token);
            handleMessage(nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String determineNicknameFromToken(String token) {
        // Implémenter la logique pour déterminer le surnom associé au token
        // ...
        return "nickname"; // Remplacer par la logique réelle
    }
}