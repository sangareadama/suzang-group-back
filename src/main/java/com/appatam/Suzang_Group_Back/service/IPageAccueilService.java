package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;

public interface IPageAccueilService {

	public PageAccueil enregistrerPageAccueil(PageAccueil pageAccueil);
	 
	public PageAccueil rechercherPageAccueil(Long id);
	
	public List<PageAccueil>ListePageAccueil();  
	
	
    public PageAccueil modifierPageAccueil(PageAccueil pageAccueil);
	
	public void supprimerPageAccueil(PageAccueil pageAccueil);
	
	public void  ajouterContenuDePageAuBloc(Long idBloc, Long idCont);
	
	public ContenuDePage rechercherContenuDePage(Long id);
	
    /*public ContenuDePage  enregistrerContenuDePage(ContenuDePage contenuDePage);
    
    public PageAccueil modifierContenuDePage(ContenuDePage contenuDePage);
    
    public ContenuPageTemp  enregistrerContenuPageTemp(ContenuPageTemp contenuPageTemp);
    
    public void supprimerContenuPageTemp(ContenuPageTemp ContenuPageTemp);
    
    public PageAccueil supprimerContenuDePage(ContenuDePage contenuDePage);
    
    public ContenuPageTemp rechercherContenuPageTemp(Long id);
	
	
	
	public List<ContenuDePage> ListeContenuDePage();
	
	public List<ContenuPageTemp> ListeContenuPageTemp();
	
	public ContenuDePage rechercherContenuDePage(Long id);*/
}
