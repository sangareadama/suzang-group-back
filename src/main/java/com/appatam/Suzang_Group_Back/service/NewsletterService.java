package com.appatam.Suzang_Group_Back.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.appatam.Suzang_Group_Back.dao.NewsletterDao;
import com.appatam.Suzang_Group_Back.dao.StatNewsletterDao;
import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;

@Service
@Transactional
public class NewsletterService implements INewsletterService {
	
	@Autowired
	NewsletterDao newsletterDao;
	
	@Autowired
    StatNewsletterDao statNewsletterDao;
	
	
	static int quantiteNewsletter=0;

	@Override
	public Newsletter enregistrerNewsletter(Newsletter newsletter) {
		// TODO Auto-generated method stub		
		quantiteNewsletter+=1;
		
		return newsletterDao.save(newsletter);
	}

	@Override
	public Newsletter rechercherNewsletter(Long id) {
		// TODO Auto-generated method stub
		Optional<Newsletter> nl= newsletterDao.findById(id);;
		
		return nl.get();
	}

	@Override
	public List<Newsletter> ListeNewsletter() {
		// TODO Auto-generated method stub
		return newsletterDao.findAll();
	}

	@Override
	public Newsletter rechercherNewsletterParEmail(String email) {
		// TODO Auto-generated method stub
		return newsletterDao.findByEmail(email);
	}

	@Override
	public Newsletter dessouscrirNewsletterParEmail(Newsletter newsletter) {
		// TODO Auto-generated method stub
		
		Newsletter nl  = newsletterDao.findByEmail(newsletter.getEmail());
		
		nl.setValid(false);
		
		return newsletterDao.save(nl);

	}
	
	@Override
	public void SupprimerNewsletterParId(Long id) {
		// TODO Auto-generated method stub
		newsletterDao.deleteById(id);
	}
	
	 // Annotation
	@Component
	// Class
	@Service
	@Transactional
	public class StatNewsletterService implements IStatNewsletterService {
		
		@Autowired
	    StatNewsletterDao statNewsletterDao;
		
	 
	    // Method
	    // To trigger the scheduler every 3 seconds with
	    // an initial delay of 5 seconds.
	    @Scheduled(fixedRate =60*60*1000) 
	 
	    public void scheduleTask()
	    {
	    	
	    	
	    	
	    	Date date = new Date();
	    	SimpleDateFormat minute = new SimpleDateFormat("mm");
			SimpleDateFormat heure = new SimpleDateFormat("HH");
			SimpleDateFormat jour = new SimpleDateFormat("dd");
			SimpleDateFormat mois = new SimpleDateFormat("MM");
			SimpleDateFormat annee = new SimpleDateFormat("yyyy");;
			SimpleDateFormat dateMois = new SimpleDateFormat("MMMM yyyy",new Locale("en", "US"));
			String Sminute=minute.format(date);
			String SHeure= heure.format(date);
			String SJour= jour.format(date);
			String SMois= mois.format(date);   
			String SAnnee= annee.format(date);
			String SdateMois= dateMois.format(date);
			
			System.out.println(";;;;;;;;;;;;;;;;"+SAnnee+" "+SMois+" "+SJour+" "+SHeure+ " "+Sminute+"/ "+SdateMois);
	    	StatNewsletter stn = new StatNewsletter();
	    	
	    	int min=0;
	    	int max=30;
	    	
	    	int qte=(int)(Math.random()*(max-min+1)+min);
	    	
	    	
	    	//System.out.println(";;;;;;;;;;;;;;;; avant "+quantiteNewsletter);
	    	System.out.println(";;;;;;;;;;;;;;;; avant "+qte);
	   	
	    	stn.setQuantite(qte);
	    	stn.setMinute(Sminute);
	    	stn.setHeure(SHeure);
	    	stn.setJour(SJour);
	    	stn.setMois(SMois);
	    	stn.setAnnee(SAnnee);
	    	stn.setDateMois(SdateMois.toLowerCase());
	    	quantiteNewsletter=0;
	    	
	    	System.out.println(";;;;;;;;;;;;;;;; apres "+quantiteNewsletter);
		   	
	    	statNewsletterDao.save(stn);
	    	
	    	List<StatNewsletter> liste= ListeStatNewsletter();
	    	
	   /* for (StatNewsletter statNewsletter : liste) {
	    		
	    		if(statNewsletter.getDate()==null) {
	    			
	    			statNewsletterDao.deleteById(statNewsletter.getId());
	    		}	
			}
	    	
	       /* SimpleDateFormat dateFormat = new SimpleDateFormat(
	            "dd-MM-yyyy HH:mm:ss.SSS");
	 
	        String strDate = dateFormat.format(new Date());
	 
	        System.out.println(
	            "Fixed Delay Scheduler: Task running at - "
	            + strDate);*/
	    }


		@Override
		public StatNewsletter enregistrerStatNewsletter(StatNewsletter statNewsletter) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public StatNewsletter rechercherStatNewsletter(Long id) {
			// TODO Auto-generated method stub
			Optional<StatNewsletter> nl= statNewsletterDao.findById(id);;
			
			return nl.get();
		}


		@Override
		public List<StatNewsletter> ListeStatNewsletter() {
			// TODO Auto-generated method stub
			return statNewsletterDao.findAll();
		}


		@Override
		public StatNewsletter rechercherStatNewsletterParJour(String jour) {
			// TODO Auto-generated method stub
			return statNewsletterDao.findByJour(jour);
		}


		@Override
		public StatNewsletter rechercherStatNewsletterParMois(String mois) {
			// TODO Auto-generated method stub
			return statNewsletterDao.findByMois(mois);
		}

		@Override
		public List<StatNewsletter> findByDateMois(String mois) {
			// TODO Auto-generated method stub
			System.out.println("!!!!!!!!!!!!!! "+mois); 
			return statNewsletterDao.findByDateMois(mois);
		}


		@Override
		public List<StatNewsletter> findByAnnee(String annee) {
			// TODO Auto-generated method stub
			System.out.println("!!!!!!!!!!!!!! "+annee); 
			
			List<StatNewsletter> st = statNewsletterDao.findByAnnee(annee);
			  
			for (StatNewsletter statNewsletter : st) {
				
				System.out.println(statNewsletter.getAnnee());
			}
			
			return statNewsletterDao.findByAnnee(annee);
		}
	}

	
	

}
