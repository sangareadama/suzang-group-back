package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appatam.Suzang_Group_Back.dao.NousContacterDao;
import com.appatam.Suzang_Group_Back.domain.NousContacter;

@Service
@Transactional
public class NousContacterService implements INousContacterService {
	
	@Autowired
	NousContacterDao iNousContacterDao;
	

	@Override 
	public NousContacter enregistrerNousContacter(NousContacter nousContacter) {
		// TODO Auto-generated method stub
		return iNousContacterDao.save(nousContacter);
		
	}

	@Override
	public NousContacter rechercherNousContacterParEmail(String email) {
		// TODO Auto-generated method stub
		return iNousContacterDao.findByEmail(email);
	}

	@Override
	public List<NousContacter> ListeNousContacter() {
		// TODO Auto-generated method stub
		return iNousContacterDao.findAll();
	}

	@Override
	public NousContacter modifier(NousContacter nousContacter) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerNousContacter(NousContacter nousContacter) {
		// TODO Auto-generated method stub

	} 

	@Override
	public void NousContacterVus(NousContacter nousContacter) {
		// TODO Auto-generated method stub
		
		NousContacter cp= iNousContacterDao.findByEmail(nousContacter.getEmail());
		
		cp.setVus(true);
		
		iNousContacterDao.save(cp);
		
	}

}
