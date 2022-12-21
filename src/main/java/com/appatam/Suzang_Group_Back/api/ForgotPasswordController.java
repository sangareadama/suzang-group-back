package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.Model.ForgotPasswordModel;
import com.appatam.Suzang_Group_Back.Model.PasswordResetModel;
import com.appatam.Suzang_Group_Back.Model.Response;
import com.appatam.Suzang_Group_Back.domain.PasswordRessetToken;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;
import com.appatam.Suzang_Group_Back.service.EmailSenderService;
import com.appatam.Suzang_Group_Back.service.IPasswordResetTokenService;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;

@RestController
@RequestMapping("/home")

public class ForgotPasswordController {
	
	@Autowired
	IUtilisateurService iUtilisateurService;
	
	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	
    @Autowired
    IPasswordResetTokenService iPasswordResetTokenService;
	
	@PostMapping("/forgotPassword")
	 public ResponseEntity<SimpleMailMessage> sendEmail(@RequestBody ForgotPasswordModel fp, final HttpServletRequest request) {
		
		final String email = fp.getUsername();
		
		Utilisateur ut = iUtilisateurService.rechercherUtilisateur(email);
		
		 if (ut == null){
			 
	        //	return new com.appatam.Suzang_Group_Back.Model.Response(1,"mail non trouvé");
	        }
		 
		  final PasswordRessetToken token = new PasswordRessetToken();
	        //CrÃ©ation du token
		  
	        token.setToken(UUID.randomUUID().toString());
	         
	        token.setUtilisateur(ut);
	        
	        //Le token expire dans 30 minutes
	        token.setExpiryDate(30);
	        
	        //Sauvegarde du token
	      
	        iPasswordResetTokenService.save(token); 
	         
	        final String hoteOrigin =  request.getHeader("origin");
	        
	        
	        String subject ="Suzang_Group : Reinitialisation de mot de Pass. ";
	        
	        String body=" Pour reinitialiser votre mot de pass , veillez clicque sur le lien suivant : "+ hoteOrigin + "/#/resetPassword?token=" + token.getToken();
			
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/forgotPassword").toUriString());
			
			return ResponseEntity.created(uri).body(senderService.sendeEmail(email,subject  , body));
	        
		
	}
	
	@GetMapping("/resetPassword/{token}")
	 public Object veriflyResetPassword(@PathVariable String token) {
		
		PasswordRessetToken passwordResetToken = iPasswordResetTokenService.findByToken(token) ;
		
		 //Si pas de token
        if (passwordResetToken == null){
           return new Response(1, "Demande pas autorisee") ;
        //Si le lien a expirÃ© ou 
        } else if (passwordResetToken.isExpired() || !passwordResetToken.isValid()){
        	return new Response(2, "Le lien a expirÃ©");
        	
        } else {
          return new Response(0, "Demande acceptee") ;
        }
        
	}
	
	
	
	
	@PostMapping("/resetPassword/{token}")
	   public Object resetPassword(@PathVariable(name = "token") String token,
	    							@RequestBody PasswordResetModel passwordResetModel) {

	    	
	    	if(passwordResetModel.getPassword().equals(passwordResetModel.getConfirmPassword())) {
	    		
	    		PasswordRessetToken passwordResetToken = iPasswordResetTokenService.findByToken(token) ;
	    		
	    		Utilisateur ut = passwordResetToken.getUtilisateur(); 
	    			 
	    		ut.setPassword(passwordResetModel.getPassword());
	              
	            iUtilisateurService.enregistrerUtilisateur(ut);
	           
	            passwordResetToken.setValid(false);
	           
	            return new Response(0, "Mot de passe modifie avec succes");
	    	}
	    	 
	    	return new Response(1, "Les mots de passes ne correspondent pas");

	        
	    }
		


}
