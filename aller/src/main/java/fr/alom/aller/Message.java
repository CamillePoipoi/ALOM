package fr.alom.aller;
public class Message {

	private String destinataire;
	private String content;
	private String tokenEnvoyeur;
	
	public Message() {
	}

	public Message(String destinataire, String content, String tokenEnvoyeur) {
		this.destinataire = destinataire;
		this.content = content;
		this.tokenEnvoyeur = tokenEnvoyeur;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTokenEnvoyeur() {
		return tokenEnvoyeur;
	}

	public void setTokenEnvoyeur(String tokenEnvoyeur) {
		this.tokenEnvoyeur = tokenEnvoyeur;
	}
	
	
}
