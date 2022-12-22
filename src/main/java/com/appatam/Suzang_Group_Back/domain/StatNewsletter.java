package com.appatam.Suzang_Group_Back.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class StatNewsletter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String minute;   
	private String heure;
	private String jour;
	private String mois;  
	private String annee;
	private int quantite;
	private String dateMois;
	private Date date;
	
	
	
	public StatNewsletter() {
		super();
		this.date=new Date();
		// TODO Auto-generated constructor stub
	}
	public StatNewsletter(String minute,String heure, String jour, String mois, String annee, int quantite,String dateMois) {
		super();
		this.minute=minute;
		this.heure = heure;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		this.quantite = quantite;
		this.dateMois=dateMois;  
		this.date=new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getMinute() { 
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getDateMois() {
		return dateMois;
	}
	public void setDateMois(String dateMois) {
		this.dateMois = dateMois;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
