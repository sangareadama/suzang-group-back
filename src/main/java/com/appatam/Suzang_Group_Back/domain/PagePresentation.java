package com.appatam.Suzang_Group_Back.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class PagePresentation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;  
	private String image;
	@ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<ContenuDePage> contenus;
	
	
	
	public PagePresentation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Collection<ContenuDePage> getContenus() {
		return contenus;
	}
	public void setContenus(Collection<ContenuDePage> contenus) {
		this.contenus = contenus;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	

}
