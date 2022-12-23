package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.IOffreDao;
import com.appatam.Suzang_Group_Back.domain.Offre;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class OffreService implements IOffreService {
	
	@Autowired
	IOffreDao iOffreDao;

	@Override
	public Offre enregistrerOffre(Offre offre) {
		// TODO Auto-generated method stub
		return iOffreDao.save(offre);
	}

	@Override
	public Offre rechercherOffreParId(Long id) {
		// TODO Auto-generated method stub
		Optional<Offre> of= iOffreDao.findById(id);
		
		return of.get();
	}

	@Override
	public List<Offre> ListeOffre() {
		// TODO Auto-generated method stub
		return iOffreDao.findAll();
	}

	@Override
	public Offre modifierOffre(Offre offre) {
		// TODO Auto-generated method stub
		Offre of = rechercherOffreParId(offre.getId());
		
		of.setTitre(offre.getTitre());
		
		of.setLibelle(offre.getLibelle());
		
		of.setNbrePlace(offre.getNbrePlace());
		
		return iOffreDao.save(of) ;
	}

	@Override
	public void supprimerOffre(Offre offre) {
		// TODO Auto-generated method stub
		iOffreDao.deleteById(offre.getId());
	}

	@Override
	public void ajouterPostulantAOffre(Long Postul, Long of) {
		// TODO Auto-generated method stub

	}

}
