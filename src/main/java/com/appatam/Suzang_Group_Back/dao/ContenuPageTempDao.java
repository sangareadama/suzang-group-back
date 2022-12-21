package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;

public interface ContenuPageTempDao extends JpaRepository<ContenuPageTemp, Long> {

	@Query("SELECT t FROM ContenuPageTemp t ORDER BY t.id ASC")
	List<ContenuPageTemp> findAll();
}
