package fr.alom.retour.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import fr.alom.retour.back.MapNicknameSocket;

public class ConnexionClient extends Thread {
    private Socket socket;
    private String token;
    private String nickname;
    MapNicknameSocket mapNicknameSocket;

    public ConnexionClient(Socket socket, MapNicknameSocket mapNicknameSocket) {
        this.socket = socket;
        this.mapNicknameSocket = mapNicknameSocket;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            Boolean isRunning = true;

            out.println("Entrez votre token : ");
            String receivedToken = in.readLine(); // Récupère le token envoyé par le client

            // Simuler l'appel au back pour récupérer le nickname associé au token
            String nickname = retrieveNicknameFromToken(receivedToken);

            // Envoyer le nickname au client et l'enregistrer
            mapNicknameSocket.add(nickname, socket);
            out.println("Nickname associé au token : " + nickname);

            while(isRunning){
                if(receivedToken.equals("close")){
                    // Fermeture des flux et de la socket
                    in.close();
                    out.close();
                    socket.close();
                    isRunning=false;
                }
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode de simulation pour récupérer le nickname associé au token
    private String retrieveNicknameFromToken(String token) {
        // Simuler une recherche du nickname associé au token 
        // Ici, on renvoie un nickname simulé pour le token reçu
        if (token.equals("token")) {
            return "Nickname Simule";
        } else {
            return "Token non reconnu";
        }
    }

    
}
