package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.IVisiteursDao;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;
import com.appatam.Suzang_Group_Back.domain.Visiteurs;

// Annotation
@Component
@Service
@Transactional
public class VisiteursService implements IVisiteursService {
	
	@Autowired
	IVisiteursDao iVisiteursDao;

	@Override
	public Visiteurs enregistrerVisiteurs(Visiteurs Visiteurs) {
		// TODO Auto-generated method stub
		return iVisiteursDao.save(Visiteurs);
	}

	@Override
	public Visiteurs rechercherVisiteursId(Long id) {
		// TODO Auto-generated method stub
		Optional<Visiteurs> vs= iVisiteursDao.findById(id);;
		
		return vs.get();
	}

	@Override
	public List<Visiteurs> ListeVisiteurs() {
		// TODO Auto-generated method stub
		return iVisiteursDao.findAll();
	}

}
