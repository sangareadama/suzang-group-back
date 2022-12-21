package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.ServiceItemDao;
import com.appatam.Suzang_Group_Back.domain.ServicesItem;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
@Transactional
public class ServicesItemService implements IServiceItemService {
	
	@Autowired
	ServiceItemDao iServiceItemDao; 

	@Override
	public ServicesItem enregistrerServicesItem(ServicesItem servicesItem) {
		// TODO Auto-generated method stub
		return iServiceItemDao.save(servicesItem);
	}

	@Override
	public ServicesItem rechercherServicesItem(Long id) {
		// TODO Auto-generated method stub
		Optional<ServicesItem> is= iServiceItemDao.findById(id);
		
		return is.get();
	}
 
	@Override
	public List<ServicesItem> ListeServicesItem() {
		// TODO Auto-generated method stub
		return iServiceItemDao.findAll();
	}

	@Override
	public ServicesItem modifierServicesItem(ServicesItem ServicesItem) {
		
		ServicesItem si = rechercherServicesItem(ServicesItem.getId());
		
		si.setTitre1(ServicesItem.getTitre1());
		
		si.setContenu1(ServicesItem.getContenu1());
		
		return iServiceItemDao.save(si);
		
	}

	@Override
	public void supprimerServicesItem(ServicesItem servicesItem) {
		// TODO Auto-generated method stub
		
		iServiceItemDao.deleteById(servicesItem.getId());
		
	}
	
	 

}
