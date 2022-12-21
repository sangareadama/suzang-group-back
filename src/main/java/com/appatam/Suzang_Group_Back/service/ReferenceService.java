package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.IReferenceDao;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.domain.Reference;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReferenceService implements IReferenceService {
	
	@Autowired
	IReferenceDao  iReferenceDao;

	@Override
	public Reference enregistrerReference(Reference reference) {
		
		Reference rf = new Reference();
	       
		rf.setNom(reference.getNom());
		
		rf.setImage(reference.getImage());
	     
		return iReferenceDao.save(rf);
	}

	@Override
	public Reference rechercherReference(Long id) {
		// TODO Auto-generated method stub
		Optional<Reference> rf= iReferenceDao.findById(id);
		
		return rf.get();
	}

	@Override
	public List<Reference> ListeReference() {
		// TODO Auto-generated method stub
		return iReferenceDao.findAll();
	}

	@Override
	public Reference modifierReference(Reference reference) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerReference(Reference reference) {
		// TODO Auto-generated method stub
		iReferenceDao.deleteById(reference.getId());

	}

}
