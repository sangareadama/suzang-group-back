package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.PresentationPage;

public interface IPresentationPageService {
	
    public PresentationPage enregistrerPresentation(PresentationPage presentationPage);
		
	public PresentationPage rechercherPresentation(Long id);
	
	public List<PresentationPage>ListePresentation();
	
    public PresentationPage modifierPresentation(PresentationPage presentationPage);
	
	
	public void supprimerPresentation(PresentationPage presentationPage);
	
	
  
}
