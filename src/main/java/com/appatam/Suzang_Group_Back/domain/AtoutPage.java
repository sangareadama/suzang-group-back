package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class AtoutPage {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre1;
	@Column(length = 2000)
	private String contenu1;
	@Column(length = 2000)
	private String contenu2;
	
	
	
	public AtoutPage( String titre1, String contenu1, String contenu2) {
		super();
		
		this.titre1 = titre1;
		this.contenu1 = contenu1;
		this.contenu2 = contenu2;
	}
	public AtoutPage() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getContenu2() {
		return contenu2;
	}
	public void setContenu2(String contenu2) {
		this.contenu2 = contenu2;
	}
}
