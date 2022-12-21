package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ServicePage;

public interface IServicePageService {
	
	public ServicePage enregistrerServicePage(ServicePage servicePage);
	 
	public ServicePage rechercherServicePage(Long id);
	
	public List<ServicePage>ListeServicePage(); 
	
    public ServicePage modifierServicePage(ServicePage servicePage);
	
	public void supprimerServicePage(ServicePage servicePage);

}
