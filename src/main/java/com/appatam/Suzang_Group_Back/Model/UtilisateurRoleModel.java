package com.appatam.Suzang_Group_Back.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UtilisateurRoleModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	private String libelle; 
	    
	
	public String getLibelle() { 
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}  

}
