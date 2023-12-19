package fr.alom.aller;

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
            ps.println("Bienvenue sur la socket" + socket);
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
    
    Message getMessageFromClient() {
    	Scanner scanner;
    	ps.println("entrez votre token");
    	Message m = new Message();
    	try {
            scanner = new Scanner(socket.getInputStream());
            m.setTokenEnvoyeur(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	ps.println("entrez votre destinataire");
    	try {
            scanner = new Scanner(socket.getInputStream());
            m.setDestinataire(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	ps.println("Entrez votre message");
    	try {
            scanner = new Scanner(socket.getInputStream());
            m.setContent(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	return m;
    }

    public void run() {
    
        try {
            // Traitement à effectuer lors de la réception d'un message sur cette socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            
            while ((message = in.readLine()) != null) {
            	String messageContent[] = message.split(" ");
            	if(messageContent[0].equals("TOKEN")) {
            		this.token = getTokenFromClient();
            		//Verification du token aupres du microservice UserManager
                    ps.println(token);
            	}
            	if(messageContent[0].equals("SEND")) {
            		Message msgToSend = getMessageFromClient();
                    System.out.println("FROM " + msgToSend.getTokenEnvoyeur() + ", TO : "+ msgToSend.getDestinataire() + " : " + msgToSend.getContent());
                    //Envoi du message au microservice MessageManager
            	}   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}