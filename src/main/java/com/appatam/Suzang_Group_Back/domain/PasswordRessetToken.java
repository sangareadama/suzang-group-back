package com.appatam.Suzang_Group_Back.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class PasswordRessetToken {
	
	    @Id  
	    private String token; 

	    @ManyToOne
	    private Utilisateur utilisateur;

	    private Date date ; 
	    
	    private Date expiryDate;
	    
	    //Vrai si la modification est toujours permise
	    @Column(nullable = true)
	    private boolean valid ;
	    
	   
	    
		public PasswordRessetToken() {
			super();
			this.date = new Date() ; 
			this.valid = true ;
			// TODO Auto-generated constructor stub
		}

		public String getToken() { 
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Utilisateur getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}
		
		public void setExpiryDate(Date expiryDate) {
	        this.expiryDate = expiryDate;
	    }

		//Precise l'heure d'expiration
	    public void setExpiryDate(int minutes){
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MINUTE, minutes);
	        this.expiryDate = cal.getTime(); 
	    }

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}

		 //Verifie si la date a expiré
	    public boolean isExpired() {
	        return new Date().after(this.expiryDate);
	    }
	    
	    
}
