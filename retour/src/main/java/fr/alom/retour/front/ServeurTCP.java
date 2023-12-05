package fr.alom.retour.front;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import fr.alom.retour.back.MapNicknameSocket;

public class ServeurTCP {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur
        int port = 8080; // Port du serveur
        Boolean isRunnable = true;
        MapNicknameSocket mapNicknameSocket = new MapNicknameSocket();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Connecté au serveur " + serverAddress + ":" + port);

            //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //PrintStream output = new PrintStream(socket.getOutputStream());

            //Scanner scanner = new Scanner(System.in);

            while (isRunnable) {
                // Accepter une connexion entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion entrante : " + clientSocket);

                // Créer un objet ConnexionClient pour gérer la connexion
                ConnexionClient connexionClient = new ConnexionClient(clientSocket, mapNicknameSocket);
                String nickname = connexionClient.getNickname();

                mapNicknameSocket.add(nickname, clientSocket); // Associer la connexion à un token dans la HashMap

                connexionClient.start(); // Démarrer le traitement de la connexion dans un nouveau thread
            }

            // Demande au client d'entrer son token
            //System.out.println(input.readLine()); // Attend le message du serveur demandant le token
            //String token = scanner.nextLine();
            //output.println(token); // Envoie le token au serveur

            // Lecture du nickname associé au token envoyé
            //String serverResponse = input.readLine();
            //System.out.println("Réponse du serveur : " + serverResponse);

            //Appel au Microservice de gestion de token/nickname
            //String nickname = "nickanme1";


            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
