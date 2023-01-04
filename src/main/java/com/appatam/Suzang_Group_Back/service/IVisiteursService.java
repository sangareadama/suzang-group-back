package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.Visiteurs;

public interface IVisiteursService  {
	
	public Visiteurs enregistrerVisiteurs(Visiteurs Visiteurs);
	 
	public Visiteurs rechercherVisiteursId(Long id);
	
	public List<Visiteurs>ListeVisiteurs(); 
	
	public List<Visiteurs>ListeVisiteursStat(); 
}
