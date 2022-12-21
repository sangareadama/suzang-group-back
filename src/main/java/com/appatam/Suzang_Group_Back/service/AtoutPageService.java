package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.AtoutPageDao;
import com.appatam.Suzang_Group_Back.domain.AtoutPage;

@Service
@Transactional
public class AtoutPageService implements IAtoutPageSevice {

	@Autowired
	AtoutPageDao AtoutPageDao;
	
	@Override
	public AtoutPage enregistrerAtoutPage(AtoutPage atoutPage) {
		// TODO Auto-generated method stub
		return AtoutPageDao.save(atoutPage);
	}

	@Override
	public AtoutPage rechercherAtoutPage(Long id) {
		
		Optional<AtoutPage> pa= AtoutPageDao.findById(id);
		
		return pa.get();
	}

	@Override
	public List<AtoutPage> ListeAtoutPage() {
		// TODO Auto-generated method stub
		return AtoutPageDao.findAll();
	}

	@Override
	public AtoutPage modifierAtoutPage(AtoutPage atoutPage) {
		// TODO Auto-generated method stub
		AtoutPage ap =  rechercherAtoutPage(atoutPage.getId());
		
		ap.setTitre1(atoutPage.getTitre1());
		
		ap.setContenu1(atoutPage.getContenu1());
		
		ap.setContenu2(atoutPage.getContenu2());
		
		return AtoutPageDao.save(ap);
	}

	@Override
	public void supprimerAtoutPage(AtoutPage atoutPage) {
		// TODO Auto-generated method stub
		AtoutPageDao.deleteById(atoutPage.getId());
	}

}
