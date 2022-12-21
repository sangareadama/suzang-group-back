package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;

public interface StatNewsletterDao extends JpaRepository<StatNewsletter, Long>  {
	
	StatNewsletter findByJour(String jour);
	
	StatNewsletter findByMois(String mois);
	
	List<StatNewsletter> findByDateMois(String mois);
	
	List<StatNewsletter> findByAnnee(String annee);
	
	/*@Query("select p.dateMois from StatNewsletter p")
	List<Object>ListeDesMois();
	@Query("select p.heure from StatNewsletter p")
	List<Object>ListeDesHeure(); */

}
