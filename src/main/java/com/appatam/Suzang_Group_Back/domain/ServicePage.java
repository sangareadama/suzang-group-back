package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class ServicePage {

	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre1;  
	private String contenu1;  
	 
	public Long getId() { 
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public ServicePage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServicePage( String titre1, String contenu1) {
		super();
		this.titre1 = titre1;
		this.contenu1 = contenu1;
	}
	public String getTitre1() {
		return titre1;
	}
	public void setTitre1(String titre1) {
		this.titre1 = titre1;
	}
	public String getContenu1() {
		return contenu1;  
	}
	public void setContenu1(String contenu1) {
		this.contenu1 = contenu1;
	}
}
