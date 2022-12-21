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
import com.appatam.Suzang_Group_Back.dao.PagePresentationDao;
import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.PagePresentation;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;


@Service
@Transactional
public class PagePresentationService implements IPagePresentationService {
	
	@Autowired
	PagePresentationDao pagePresentationDao;
	   
	@Autowired
	ContenuDePageDao contenuDePageDao;
	
	@Autowired 
	ContenuPageTempDao contenuPageTempDao;
	

	@Override
	public PagePresentation enregistrerPagePresentation(PagePresentation pagePresentation) {
		// TODO Auto-generated method stub
		PagePresentation pp = new PagePresentation();
			
			pp.setTitre(pagePresentation.getTitre());
		
			pp.setContenus(new ArrayList<>()); 
			
			pp.setImage(pagePresentation.getImage());
		
		return pagePresentationDao.save(pp);
	}

	@Override
	public PagePresentation rechercherPagePresentationParId(Long id) {
		
		Optional<PagePresentation> pp= pagePresentationDao.findById(id);
		
		return pp.get();
	}

	@Override
	public List<PagePresentation> ListePagePresentation() {
		// TODO Auto-generated method stub
		return pagePresentationDao.findAll();
	}

	@Override
	public PagePresentation modifierPagePresentation(PagePresentation pagePresentation) {
		// TODO Auto-generated method stub
		PagePresentation pp = rechercherPagePresentationParId(pagePresentation.getId());
		
		pp.setContenus(pp.getContenus()); 
		
		pp.setImage(pagePresentation.getImage());
		
		return pagePresentationDao.save(pp);
	}

	@Override
	public void supprimerPagePresentation(PagePresentation pagePresentation) {
		// TODO Auto-generated method stub
		pagePresentationDao.deleteById(pagePresentation.getId());
	}

	@Override
	public void ajouterContenuDePageAuBloc(Long idBloc, Long idCont) {
		// TODO Auto-generated method stub
		PagePresentation pp = rechercherPagePresentationParId(idBloc);
		
		ContenuDePage cdp = rechercherContenuDePage(idCont); 
					 
		pp.getContenus().add(cdp);    
	}

	@Override
	public ContenuDePage rechercherContenuDePage(Long id) {
		// TODO Auto-generated method stub
		Optional<ContenuDePage> cp= contenuDePageDao.findById(id);
		
		return cp.get();
	}

	
	@Override
	public PagePresentation modifierContenuDePage(ContenuDePage contenuDePage) {
		// TODO Auto-generated method stub
		ContenuDePage ctp = rechercherContenuDePage(contenuDePage.getId());
		
		ctp.setLibelle(contenuDePage.getLibelle());
		
		ctp.setDecalageHaut(contenuDePage.getDecalageHaut());
		
		ctp.setDecalageGauche(contenuDePage.getDecalageGauche());
		
		List<PagePresentation> blocs = ListePagePresentation();
		
		//recherche le bloc du contenu et le retourner
		PagePresentation returnBloc = null;
		
		contenuDePageDao.save(ctp);
		
		for (PagePresentation pagePresentation : blocs) {
			
			if(pagePresentation.getContenus().contains(ctp)) {
				
				returnBloc=pagePresentation;
			}
		}
		
		return returnBloc;  	
	}
	
	@Override
	public PagePresentation supprimerContenuDePage(ContenuDePage contenuDePage) {
		
		ContenuDePage ctp = rechercherContenuDePage(contenuDePage.getId());
		
		List<PagePresentation> blocs = ListePagePresentation();
		
		//recherche le bloc du contenu et le retourner
		PagePresentation returnBloc = null;
		
		for (PagePresentation pagePresentation : blocs) {
			
			if(pagePresentation.getContenus().contains(ctp)) {
				
				returnBloc=pagePresentation;
				
				pagePresentation.getContenus().remove(ctp);
				
			} 
		}
		  
		contenuDePageDao.deleteById(contenuDePage.getId());
		// TODO Auto-generated method stub
		return returnBloc ;
	}

}
