package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.PresentationPageDao;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class PresentationPageService implements IPresentationPageService {

	@Autowired
	PresentationPageDao ipresentationDao;
	
	@Override
	public PresentationPage enregistrerPresentation(PresentationPage presentationPage) {
		// TODO Auto-generated method stub
		return ipresentationDao.save(presentationPage);
	}

	@Override
	public PresentationPage rechercherPresentation(Long id) {
		// TODO Auto-generated method stub
		Optional<PresentationPage> ps= ipresentationDao.findById(id);
		
		return ps.get();
	}

	@Override
	public List<PresentationPage> ListePresentation() {
		// TODO Auto-generated method stub
		return ipresentationDao.findAll();
	}

	@Override
	public PresentationPage modifierPresentation(PresentationPage presentationPage) {
		// TODO Auto-generated method stub
		
		PresentationPage ps = rechercherPresentation(presentationPage.getId());
		
		ps.setTitre1(presentationPage.getTitre1());
		
		ps.setContenu1(presentationPage.getContenu1());
		
		
		return ipresentationDao.save(ps);    
	}

	@Override
	public void supprimerPresentation(PresentationPage presentationPage) {
		// TODO Auto-generated method stub
		ipresentationDao.deleteById(presentationPage.getId());

	}

}
