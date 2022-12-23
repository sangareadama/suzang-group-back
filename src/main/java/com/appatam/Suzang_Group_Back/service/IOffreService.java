package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.Offre;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;

public interface IOffreService {
	
	public Offre enregistrerOffre(Offre offre);
	 
	public Offre rechercherOffreParId(Long id);
	
	public List<Offre>ListeOffre();  
	
    public Offre modifierOffre(Offre offre);
	
	public void supprimerOffre(Offre offre);
	
	public void  ajouterPostulantAOffre(Long Postul, Long of);

}
