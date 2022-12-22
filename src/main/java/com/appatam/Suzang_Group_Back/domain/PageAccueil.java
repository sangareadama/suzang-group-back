package com.appatam.Suzang_Group_Back.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class PageAccueil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer ordre;  
	private String titre; 
	private String image;  
	@ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<ContenuDePage> contenus;
	
	  
	public PageAccueil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PageAccueil(int ordre, String titre, Collection<ContenuDePage> contenus, String image) {
		super();
		this.ordre=ordre;
		this.titre = titre;
		this.contenus=contenus;
		this.image = image;
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
	
	
	
	public Collection<ContenuDePage> getContenus() {
		return contenus;
	}


	public void setContenus(Collection<ContenuDePage> contenus) {
		this.contenus = contenus;
	}

 
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	public int getOrdre() {
		return ordre;
	}


	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}


	
	
	
	

}
