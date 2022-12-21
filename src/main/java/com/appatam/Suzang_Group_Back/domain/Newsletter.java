package com.appatam.Suzang_Group_Back.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Newsletter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String email;
	 //Vrai si il est toujours actif
    @Column(nullable = true)
    private boolean valid ;
    
    private Date dateCreation;
    
    

	public Newsletter() {
		super();
		this.dateCreation = new Date() ; 
		this.valid = true ;
		// TODO Auto-generated constructor stub
	}

	public Newsletter(String email,String nom) {
		super();
		this.email = email;
		this.nom=nom;
		this.dateCreation = new Date() ; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
    
    
    

}
