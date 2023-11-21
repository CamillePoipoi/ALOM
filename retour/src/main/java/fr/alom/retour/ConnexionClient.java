package fr.alom.retour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ConnexionClient extends Thread {
    private Socket socket;
    private String token;

    public ConnexionClient(Socket socket) {
        this.socket = socket;
        this.token = getTokenFromClient();
    }

    String getTokenFromClient() {
        System.out.println("Veuillez entrer votre token : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void run() {
        try {
            // Traitement à effectuer lors de la réception d'un message sur cette socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Message reçu de " + token + " : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}