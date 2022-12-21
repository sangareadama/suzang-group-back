package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class ContactPage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String adresseTitre;
	private String adresse;
	private String telephoneTitre;
	private String telephone;
	private String emailTitre;
	private String email;
	
	public ContactPage( String adresseTitre, String adresse, String telephoneTitre, String telephone,
			String emailTitre, String email) {
		super();
		
		this.adresseTitre = adresseTitre;
		this.adresse = adresse;
		this.telephoneTitre = telephoneTitre;
		this.telephone = telephone;
		this.emailTitre = emailTitre;
		this.email = email;
	}
	
	
	public ContactPage() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresseTitre() {
		return adresseTitre;
	}
	public void setAdresseTitre(String adresseTitre) {
		this.adresseTitre = adresseTitre;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephoneTitre() {
		return telephoneTitre;
	}
	public void setTelephoneTitre(String telephoneTitre) {
		this.telephoneTitre = telephoneTitre;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmailTitre() {
		return emailTitre;
	}
	public void setEmailTitre(String emailTitre) {
		this.emailTitre = emailTitre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
