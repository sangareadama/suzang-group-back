package com.appatam.Suzang_Group_Back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appatam.Suzang_Group_Back.domain.Newsletter;

public interface NewsletterDao  extends JpaRepository<Newsletter, Long>{
	
	Newsletter findByEmail(String email);

}
