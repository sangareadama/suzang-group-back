package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;

public interface IStatNewsletterService {
	
	public StatNewsletter enregistrerStatNewsletter(StatNewsletter statNewsletter);
	 
	public StatNewsletter rechercherStatNewsletter(Long id);
	
	public List<StatNewsletter>ListeStatNewsletter(); 
	 
    public StatNewsletter rechercherStatNewsletterParJour(String  jour);
    
    public StatNewsletter rechercherStatNewsletterParMois(String  mois);
	
	public List<StatNewsletter> findByDateMois(String mois);
	
	public List<StatNewsletter> findByAnnee(String annee);


}
