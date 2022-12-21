package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.AtoutPage;

public interface IAtoutPageSevice {
	
	    public AtoutPage enregistrerAtoutPage(AtoutPage atoutPage);
	 
		public AtoutPage rechercherAtoutPage(Long id);
		
		public List<AtoutPage>ListeAtoutPage(); 
		
	    public AtoutPage modifierAtoutPage(AtoutPage atoutPage);
		
		public void supprimerAtoutPage(AtoutPage atoutPage);

}
