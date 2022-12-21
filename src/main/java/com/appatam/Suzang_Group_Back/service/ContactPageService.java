package com.appatam.Suzang_Group_Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.ContactPageDao;
import com.appatam.Suzang_Group_Back.domain.ClientPage;
import com.appatam.Suzang_Group_Back.domain.ContactPage;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactPageService implements IContactPageService {
	
	@Autowired
	ContactPageDao contactPageDao;

	@Override
	public ContactPage enregistrerContactPage(ContactPage contactPage) {
		// TODO Auto-generated method stub
		return contactPageDao.save(contactPage);
	}

	@Override
	public ContactPage rechercherContactPage(Long id) {
		// TODO Auto-generated method stub
       Optional<ContactPage> ps= contactPageDao.findById(id);
		
		return ps.get();
	}

	@Override
	public List<ContactPage> ListeContactPage() {
		// TODO Auto-generated method stub
		return contactPageDao.findAll();
	}

	@Override
	public ContactPage modifierContactPage(ContactPage contactPage) {
		// TODO Auto-generated method stub
		
		ContactPage cp =  rechercherContactPage(contactPage.getId());
		
		cp.setAdresseTitre(contactPage.getAdresseTitre());
		
		cp.setAdresse(contactPage.getAdresse());
		
        cp.setTelephoneTitre(contactPage.getTelephoneTitre());
		
		cp.setTelephone(contactPage.getTelephone());
		
        cp.setEmailTitre(contactPage.getEmailTitre());
		
		cp.setEmail(contactPage.getEmail());
		
		return contactPageDao.save(cp);
	}

	@Override
	public void supprimerContactPage(ContactPage contactPage) {
		// TODO Auto-generated method stub
		contactPageDao.deleteById(contactPage.getId());
	}
	
	
}
