package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
@Entity
@AllArgsConstructor
public class Offre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String titre; 
	@Column(length = 2000)
	private String libelle; 
	private Integer nbrePlace;
	
	public Offre() { 
		super();
		// TODO Auto-generated constructor stub
	}

	public Offre(String titre, String libelle, Integer nbrePlace) {
		super();
		this.titre = titre;
		this.libelle = libelle;
		this.nbrePlace = nbrePlace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getNbrePlace() {
		return nbrePlace;
	}

	public void setNbrePlace(Integer nbrePlace) {
		this.nbrePlace = nbrePlace;
	} 
	
	
	

}
