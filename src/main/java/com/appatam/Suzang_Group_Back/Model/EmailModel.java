package com.appatam.Suzang_Group_Back.Model;

public class EmailModel {
	private String nom;
	private String prenom;
	private String contact;
	private String email;
	private String commentaire;	
	
	public EmailModel(String nom, String prenom, String contact, String email, String commentaire) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.contact = contact;
		this.email = email;
		this.commentaire = commentaire;
	}

	public EmailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCommentaire() {
		return commentaire;
	}



	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	

}
