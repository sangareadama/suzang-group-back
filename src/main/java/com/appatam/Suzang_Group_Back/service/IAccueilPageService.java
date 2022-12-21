package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.AccueilPage;

public interface IAccueilPageService {
	
	public AccueilPage enregistrerAccueilPage(AccueilPage accueilPage);
	 
	public AccueilPage rechercherAccueilPage(Long id);
	
	public List<AccueilPage>ListeAccueilPage(); 
	
    public AccueilPage modifierAccueilPage(AccueilPage accueilPage);
	
	public void supprimerAccueilPage(AccueilPage accueilPage);

}
