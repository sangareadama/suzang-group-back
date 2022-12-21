package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class ClientPage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre1;
	@Column(length = 2000)
	private String contenu1;  
	 
	public Long getId() { 
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public ClientPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPage( String titre1, String contenu1) {
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
