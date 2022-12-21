package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.ClientPageDao;
import com.appatam.Suzang_Group_Back.domain.ClientPage;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class ClientPageService implements IClientPageService {
	 
	@Autowired
	ClientPageDao iClientPageDao;

	@Override
	public ClientPage rechercherClientPage(Long id) {
		
		Optional<ClientPage> ps= iClientPageDao.findById(id);
		
		return ps.get();
	}

	@Override
	public List<ClientPage> ListeClientPage() {
		// TODO Auto-generated method stub
		return iClientPageDao.findAll();
	}

	@Override
	public ClientPage modifierClientPage(ClientPage clientPage) {
		
		ClientPage cp =  rechercherClientPage(clientPage.getId());
		
		cp.setTitre1(clientPage.getTitre1());
		
		cp.setContenu1(clientPage.getContenu1());
		
		
		return iClientPageDao.save(cp) ;
	}

	@Override
	public void supprimerClientPage(ClientPage clientPage) {
		// TODO Auto-generated method stub
		
		iClientPageDao.deleteById(clientPage.getId()); 
		
	}

	@Override
	public ClientPage enregistrerClientPage(ClientPage clientPage) {
		// TODO Auto-generated method stub
		return iClientPageDao.save(clientPage);
	}
	
}
