package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.PagePresentation;

public interface IPagePresentationService {
	
	public PagePresentation enregistrerPagePresentation(PagePresentation pagePresentation);
	 
	public PagePresentation rechercherPagePresentationParId(Long id);
	
	public List<PagePresentation>ListePagePresentation();  
	
	
    public PagePresentation modifierPagePresentation(PagePresentation pagePresentation);
	
	public void supprimerPagePresentation(PagePresentation pagePresentation);
	
	public void  ajouterContenuDePageAuBloc(Long idBloc, Long idCont);
	
	public ContenuDePage rechercherContenuDePage(Long id);
	
	public  PagePresentation modifierContenuDePage(ContenuDePage contenuDePage);
	
	 public PagePresentation supprimerContenuDePage(ContenuDePage contenuDePage);

}
