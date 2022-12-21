package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ClientPage;

public interface IClientPageService {
	
	 public ClientPage enregistrerClientPage(ClientPage clientPage);
	 
	public ClientPage rechercherClientPage(Long id);
	
	public List<ClientPage>ListeClientPage();
	
    public ClientPage modifierClientPage(ClientPage clientPage);
	
	public void supprimerClientPage(ClientPage clientPage);
	

}
