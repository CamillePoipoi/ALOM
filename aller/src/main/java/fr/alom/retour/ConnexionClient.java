package fr.alom.retour;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ConnexionClient extends Thread {
    private Socket socket;
    private String token;
    private PrintStream ps;

    public ConnexionClient(Socket socket) {
        this.socket = socket;
        try {
            ps = new PrintStream(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getTokenFromClient() {       
        ps.println("entrez le token");
            
        Scanner scanner;
        try {
            scanner = new Scanner(socket.getInputStream());
            return scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void run() {
        try {
            // Traitement à effectuer lors de la réception d'un message sur cette socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            this.token = getTokenFromClient();

            ps.println(token);
            while ((message = in.readLine()) != null) {
                System.out.println("Message reçu de " + token + " : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}