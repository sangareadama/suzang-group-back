package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.AccueilPageDao;
import com.appatam.Suzang_Group_Back.domain.AccueilPage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class AccueilPageService implements IAccueilPageService {

	@Autowired
	AccueilPageDao accueilPageDao;
	
	@Override
	public AccueilPage enregistrerAccueilPage(AccueilPage accueilPage) {
		// TODO Auto-generated method stub
		return accueilPageDao.save(accueilPage);
	}

	@Override
	public AccueilPage rechercherAccueilPage(Long id) {  
		// TODO Auto-generated method stub
		
		Optional<AccueilPage> ap= accueilPageDao.findById(id);
		
		return ap.get();
	}

	@Override
	public List<AccueilPage> ListeAccueilPage() {
		// TODO Auto-generated method stub
		return accueilPageDao.findAll();
	}

	@Override
	public AccueilPage modifierAccueilPage(AccueilPage accueilPage) {
		// TODO Auto-generated method stub
        AccueilPage ap =  rechercherAccueilPage(accueilPage.getId());
        
        System.out.println("--------------------------------------> "+accueilPage.getDecalageGauche1() 
        +accueilPage.getDecalageHaut1()); 
		
		ap.setTitre11(accueilPage.getTitre11());
		
		ap.setContenu11(accueilPage.getContenu11());
		
		ap.setDecalageGauche1(accueilPage.getDecalageGauche1());
		
		ap.setDecalageHaut1(accueilPage.getDecalageHaut1());
		
		ap.setTitre22(accueilPage.getTitre22());
		
		ap.setContenu21(accueilPage.getContenu21());
		
		ap.setContenu22(accueilPage.getContenu22());
		
		ap.setDecalageGauche2(accueilPage.getDecalageGauche2());
		
		ap.setDecalageHaut2(accueilPage.getDecalageHaut2());
		
		ap.setTitre33(accueilPage.getTitre33());
		
		ap.setContenu31(accueilPage.getContenu31());
		
		ap.setContenu32(accueilPage.getContenu32());
		
		ap.setDecalageGauche3(accueilPage.getDecalageGauche3());
		
		ap.setDecalageHaut3(accueilPage.getDecalageHaut3());
		
		return accueilPageDao.save(ap);
	}  

	@Override
	public void supprimerAccueilPage(AccueilPage accueilPage) {
		// TODO Auto-generated method stub
		
		accueilPageDao.deleteById(accueilPage.getId());
	}

}
