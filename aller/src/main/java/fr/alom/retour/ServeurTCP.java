package fr.alom.retour;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServeurTCP {
    private static HashMap<ConnexionClient, String> connexions = new HashMap<>();
    private static int port = 8080;
    private static boolean isRunnable = true;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur TCP en attente de connexions...");

            while (isRunnable) {
                // Accepter une connexion entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nouvelle connexion entrante : " + clientSocket);

                // Créer un objet ConnexionClient pour gérer la connexion
                ConnexionClient connexionClient = new ConnexionClient(clientSocket);

                connexions.put(connexionClient, "token"); // Associer la connexion à un token dans la HashMap

                connexionClient.start(); // Démarrer le traitement de la connexion dans un nouveau thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}