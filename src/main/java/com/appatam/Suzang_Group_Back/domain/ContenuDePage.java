package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class ContenuDePage {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 2000000)
	private String libelle;
	private int decalageHaut;
	private int decalageGauche;
	 
	
	public ContenuDePage(String libelle,int decalageHaut,int decalageGauche) {
		super();
		this.libelle = libelle;
		this.decalageHaut=decalageHaut;
		this.decalageGauche=decalageGauche;
	}
	public ContenuDePage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getDecalageHaut() {
		return decalageHaut;
	}
	public void setDecalageHaut(int decalageHaut) {
		this.decalageHaut = decalageHaut;
	}
	public int getDecalageGauche() {
		return decalageGauche;
	}
	public void setDecalageGauche(int decalageGauche) {
		this.decalageGauche = decalageGauche;
	}
	
	

}
