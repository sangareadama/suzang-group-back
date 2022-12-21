package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.ServicePageDao;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.domain.ServicePage;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class ServicePageService implements IServicePageService {
	
	@Autowired
	ServicePageDao IservicePageDao;

	@Override
	public ServicePage enregistrerServicePage(ServicePage servicePage) {
		// TODO Auto-generated method stub
		return IservicePageDao.save(servicePage);
	}

	@Override
	public ServicePage rechercherServicePage(Long id) {
		// TODO Auto-generated method stub
		Optional<ServicePage> sp= IservicePageDao.findById(id);
		
		return sp.get();
	}

	@Override
	public List<ServicePage> ListeServicePage() {
		// TODO Auto-generated method stub
		return IservicePageDao.findAll();
	}

	@Override 
	public ServicePage modifierServicePage(ServicePage servicePage) {
		// TODO Auto-generated method stub
		ServicePage sp = rechercherServicePage(servicePage.getId());
		
		sp.setTitre1(servicePage.getTitre1());
		sp.setContenu1(servicePage.getContenu1());
		
		return IservicePageDao.save(sp);
	}

	@Override
	public void supprimerServicePage(ServicePage servicePage) {
		// TODO Auto-generated method stub
		IservicePageDao.deleteById(servicePage.getId());

	}

}
