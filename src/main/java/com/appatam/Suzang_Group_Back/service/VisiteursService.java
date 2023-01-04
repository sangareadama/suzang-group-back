package com.appatam.Suzang_Group_Back.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.IVisiteursDao;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;
import com.appatam.Suzang_Group_Back.domain.Visiteurs;

// Annotation
@Component
@Service
@Transactional
public class VisiteursService implements IVisiteursService {
	
	@Autowired
	IVisiteursDao iVisiteursDao;

	
	@Override
	public Visiteurs enregistrerVisiteurs(Visiteurs Visiteurs) {
		// TODO Auto-generated method stub
		Date date = new Date();
		
		SimpleDateFormat dateMois = new SimpleDateFormat("dd MMMM yyyy HH:mm",new Locale("en", "US"));
		
		//Visiteurs v = new Visiteurs("ip", "ok", "lat", "long", "juste", "ok");
		
		Visiteurs.setDate(dateMois.format(date));
		
		List<String> res=	iVisiteursDao.findNumberBydate();
		
		for (String string : res) {
			
			System.out.println("----------->"+string);
		}
		
		return iVisiteursDao.save(Visiteurs);
	}

	@Override
	public Visiteurs rechercherVisiteursId(Long id) {
		// TODO Auto-generated method stub
		Optional<Visiteurs> vs= iVisiteursDao.findById(id);;
		
		return vs.get();
	}

	
	@Override
	public List<Visiteurs> ListeVisiteurs() {
		// TODO Auto-generated method stub
		System.out.println("okkkkkkkk----------");
		
	  return iVisiteursDao.findAll();
		
	}

	
	//@Scheduled(fixedRate =1000) 
	@Override
	public List<Visiteurs> ListeVisiteursStat() {
		// TODO Auto-generated method stub
//		Date date = new Date();
//		
//		SimpleDateFormat dateMois = new SimpleDateFormat("dd MMMM yyyy HH:mm",new Locale("en", "US"));
//		
//		Visiteurs v = new Visiteurs("ip", "ok", "lat", "long", "juste", "ok");
//		
//		v.setDate(dateMois.format(date));
//		  
//		iVisiteursDao.save(v);
		
	List<String> res=	iVisiteursDao.findNumberBydate();
	
	for (String string : res) {
		
		System.out.println("----------->"+string);
		
	}
		
		
		 
		 
		return null;
	}

}
