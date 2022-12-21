package com.appatam.Suzang_Group_Back.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appatam.Suzang_Group_Back.dao.PasswordResetTokenDao;
import com.appatam.Suzang_Group_Back.domain.PasswordRessetToken;


@Service
@Transactional
public class PasswordRessetTokenService implements IPasswordResetTokenService {
	
	
	 @Autowired
     PasswordResetTokenDao passwordResetTokenDao;

	@Override
	public PasswordRessetToken save(PasswordRessetToken passwordResetToken) {
		
		// TODO Auto-generated method stub
		return passwordResetTokenDao.save(passwordResetToken);
	}

	@Override
	public PasswordRessetToken findByToken(String token) {
		// TODO Auto-generated method stub
		 Optional<PasswordRessetToken> pt= passwordResetTokenDao.findById(token);
			
		 return pt.get();
	}

	@Override
	public PasswordRessetToken modifier(PasswordRessetToken passwordResetToken) {
		
		 PasswordRessetToken ps = findByToken(passwordResetToken.getToken());
		 
		 ps.setToken(passwordResetToken.getToken());
		 
		 ps.setDate(passwordResetToken.getDate());
		  
		 ps.setUtilisateur(passwordResetToken.getUtilisateur());
		 
		 
		// TODO Auto-generated method stub
		return passwordResetTokenDao.save(ps);
	}
	
	
	

}
