package com.appatam.Suzang_Group_Back.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.ContenuDePageDao;
import com.appatam.Suzang_Group_Back.dao.ContenuPageTempDao;
import com.appatam.Suzang_Group_Back.dao.PageAccueilDao;
import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;

@Service
@Transactional
public class PageAccueilService implements IPageAccueilService {
	
	@Autowired
	PageAccueilDao pageAccueilDao;
	   
	@Autowired
	ContenuDePageDao contenuDePageDao;
	
	@Autowired 
	ContenuPageTempDao contenuPageTempDao;
 
	@Override
	public PageAccueil enregistrerPageAccueil(PageAccueil pageAccueil) {
	
        PageAccueil pa = new PageAccueil();
        
        pa.setOrdre(pageAccueil.getOrdre());
		
		pa.setTitre(pageAccueil.getTitre());
		
		pa.setContenus(new ArrayList<>()); 
		
		pa.setImage(pageAccueil.getImage());
		
		return pageAccueilDao.save(pa);
	}

	@Override
	public PageAccueil rechercherPageAccueil(Long id) {
		// TODO Auto-generated method stub
		Optional<PageAccueil> ap= pageAccueilDao.findById(id);
		
		return ap.get();
	}

	@Override
	public List<PageAccueil> ListePageAccueil() {
		// TODO Auto-generated method stub
		return pageAccueilDao.findAll();
		   
	}

	@Override
	public PageAccueil modifierPageAccueil(PageAccueil pageAccueil) {
		// TODO Auto-generated method stub
		PageAccueil pa = rechercherPageAccueil(pageAccueil.getId());
		
		pa.setOrdre(pageAccueil.getOrdre());
		
		pa.setTitre(pageAccueil.getTitre()); 
		
		pa.setContenus(pa.getContenus()); 
		
		pa.setImage(pageAccueil.getImage());
		
		return pageAccueilDao.save(pa);
	}

	@Override
	public void supprimerPageAccueil(PageAccueil pageAccueil) {
		// TODO Auto-generated method stub
		pageAccueilDao.deleteById(pageAccueil.getId());
	}
	
	@Override
	public void ajouterContenuDePageAuBloc(Long idBloc, Long idCont) {
		// TODO Auto-generated method stub
		PageAccueil pa = rechercherPageAccueil(idBloc);	
		
		ContenuDePage cdp = rechercherContenuDePage(idCont); 
	 
		pa.getContenus().add(cdp);    
	}
	 
	@Override
	public ContenuDePage rechercherContenuDePage(Long id) {
		// TODO Auto-generated method stub
		Optional<ContenuDePage> cdp= contenuDePageDao.findById(id);
		
		return cdp.get();
	}

 /*
	@Override
	public ContenuDePage enregistrerContenuDePage(ContenuDePage contenuDePage) {
		// TODO Auto-generated method stub
		return contenuDePageDao.save(contenuDePage);
	}

	
	@Override
	public List<ContenuDePage> ListeContenuDePage() {
		// TODO Auto-generated method stub
		return contenuDePageDao.findAll();
	}
 
	

	@Override
	public ContenuPageTemp enregistrerContenuPageTemp(ContenuPageTemp contenuPageTemp) {
		// TODO Auto-generated method stub
		return contenuPageTempDao.save(contenuPageTemp); 
	}

	@Override
	public ContenuPageTemp rechercherContenuPageTemp(Long id) {
		// TODO Auto-generated method stub
		Optional<ContenuPageTemp> cdp= contenuPageTempDao.findById(id);
		
		return cdp.get(); 
	}
	
	@Override
	public void supprimerContenuPageTemp(ContenuPageTemp ContenuPageTemp) {
		// TODO Auto-generated method stub
		contenuPageTempDao.deleteById(ContenuPageTemp.getId());
	}

	@Override
	public List<ContenuPageTemp> ListeContenuPageTemp() {
		// TODO Auto-generated method stub
		return contenuPageTempDao.findAll();
	}

	@Override
	public PageAccueil modifierContenuDePage(ContenuDePage contenuDePage) {
		// TODO Auto-generated method stub
		ContenuDePage ctp = rechercherContenuDePage(contenuDePage.getId());
		
		ctp.setDesignation(contenuDePage.getDesignation());
		
		ctp.setLibelle(contenuDePage.getLibelle());
		
		ctp.setDecalageHaut(contenuDePage.getDecalageHaut());
		
		ctp.setDecalageGauche(contenuDePage.getDecalageGauche());
		
		List<PageAccueil> blocs = ListePageAccueil();
		
		//recherche le bloc du contenu et le retourner
		PageAccueil returnBloc = null;
		
		contenuDePageDao.save(ctp);
		
		for (PageAccueil pageAccueil : blocs) {
			
			if(pageAccueil.getContenus().contains(ctp)) {
				
				returnBloc=pageAccueil;
			}
		}
		
		return returnBloc;  	
	}

	@Override
	public PageAccueil supprimerContenuDePage(ContenuDePage contenuDePage) {
		
		ContenuDePage ctp = rechercherContenuDePage(contenuDePage.getId());
			
		List<PageAccueil> blocs = ListePageAccueil();
		
		//recherche le bloc du contenu et le retourner
		PageAccueil returnBloc = null;
		
		for (PageAccueil pageAccueil : blocs) {
			
			if(pageAccueil.getContenus().contains(ctp)) {
				
				returnBloc=pageAccueil;
				
				pageAccueil.getContenus().remove(ctp);
				
			} 
		}
		  
		contenuDePageDao.deleteById(contenuDePage.getId());
		
		return returnBloc;  
		
	}*/

}
