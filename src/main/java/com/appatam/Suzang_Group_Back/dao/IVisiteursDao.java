package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.Visiteurs;

public interface IVisiteursDao extends JpaRepository<Visiteurs, Long> {
	
	@Query("SELECT date , COUNT(*)  FROM Visiteurs GROUP BY date")
	List<String> findNumberBydate();

}
