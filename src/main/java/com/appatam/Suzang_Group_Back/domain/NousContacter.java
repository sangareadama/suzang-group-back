package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class NousContacter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;  
	private String contact;   
	private String prenom; 
	private String email; 
	private String objet; 
	@Column(length = 2000)
	private String commentaire;
	private boolean vus;
	
	public NousContacter(String nom, String contact, String prenom, String email,String objet, String commentaire) {
		super();
		this.nom = nom;
		this.contact = contact;
		this.prenom = prenom;
		this.email = email; 
		this.objet=objet;
		this.commentaire = commentaire;
		this.vus=false;
	}  
	public NousContacter() { 
		super();
		this.vus=false;
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	public boolean isVus() {
		return vus;
	}
	public void setVus(boolean vus) {
		this.vus = vus;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	} 
	
	

}
