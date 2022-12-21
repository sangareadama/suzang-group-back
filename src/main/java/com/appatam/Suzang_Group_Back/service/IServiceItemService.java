package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ServicesItem;

public interface IServiceItemService {

	public ServicesItem enregistrerServicesItem(ServicesItem servicesItem);
	 
	public ServicesItem rechercherServicesItem(Long id);
	
	public List<ServicesItem>ListeServicesItem(); 
	
    public ServicesItem modifierServicesItem(ServicesItem ServicesItem);
	
	public void supprimerServicesItem(ServicesItem servicesItem);
}
