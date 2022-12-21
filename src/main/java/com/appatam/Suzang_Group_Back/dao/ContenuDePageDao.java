package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;

public interface ContenuDePageDao extends JpaRepository<ContenuDePage, Long> {
	
	@Query("SELECT t FROM ContenuDePage t ORDER BY t.id ASC")
	List<ContenuDePage> findAll();

} 
