package com.appatam.Suzang_Group_Back.api;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.Model.UtilisateurRoleModel;
import com.appatam.Suzang_Group_Back.domain.ImageModel;
import com.appatam.Suzang_Group_Back.domain.Role;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;
import com.appatam.Suzang_Group_Back.security.JWTConstUtil;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;



@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResourcesUtilisateur {
	
	@Autowired
	private IUtilisateurService iUtilisateurService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public ResourcesUtilisateur(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		
	}

	 
	@GetMapping("/users")  
	public ResponseEntity<List<Utilisateur>>listeUtilisateur(){
		
		return ResponseEntity.ok().body(iUtilisateurService.ListeUtilisateur());
		
	}
	  
	/*@PostMapping("/users/save")
	public ResponseEntity<Utilisateur> enregistrerUtilisateur(@RequestBody Utilisateur u) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iUtilisateurService.enregistrerUtilisateur(u));
	   
	  }*/
	@PostMapping(value= {"/users/save"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Utilisateur> enregistrerUtilisateur(@RequestPart("utilisateur") Utilisateur u ,
															@RequestPart("imageFile") MultipartFile[] file ) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
		try { 
			Set<ImageModel> images=uploadImage(file); 
			
			u.setUtilisateurImage(images);
			
			System.out.println(u.getRoles());
			
			Utilisateur ut= iUtilisateurService.enregistrerUtilisateur(u);
			
			iUtilisateurService.ajouterRoleAUtilisateur(ut.getUsername(), "ROLE_USER");
			
			return ResponseEntity.created(uri).body(ut);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return null;
		 
	 // return ResponseEntity.created(uri).body(iUtilisateurService.enregistrerUtilisateur(u));
	   
	  }
	
	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		Set<ImageModel> imageModels = new HashSet<>();
		
		
		//enregistrer seulement la premiere image
		
		for (int i = 0; i <1; i++) {
			
			ImageModel imageModel= new ImageModel(multipartFiles[0].getOriginalFilename(),multipartFiles[0].getContentType(), multipartFiles[0].getBytes());
			
			imageModels.add(imageModel);
			
		}		
		
		//enregistrement de plusieurs images
		
		/*for(MultipartFile file:multipartFiles) {
			
			ImageModel imageModel= new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			
			imageModels.add(imageModel);
		}*/
		
		return imageModels;
	}
	
	@PostMapping("/users/update")
	public ResponseEntity<Utilisateur> modifierUtilisateur(@RequestBody Utilisateur ut) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iUtilisateurService.modifierUtilisateur(ut));
	   
	  }
	
	@PostMapping("/users/delete")
	public void supprimerUtilisateur(@RequestBody Utilisateur ut) {
		
		System.out.println("suppression");
	  iUtilisateurService.supprimerUtilisateur(ut);
	   
	  }
	
	@GetMapping("/role/liste") 
	public List <Role> ListeRole(){ 
		
		return iUtilisateurService.ListeRole();
		
	}
	
	@PostMapping("/role/save")  
	public ResponseEntity<Role> enregistrerRole(@RequestBody Role role) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		  
	  return ResponseEntity.created(uri).body(iUtilisateurService.enregistrerRole(role));
	   
	  }
	  
	@PostMapping("/users/role/addtoUser")
	public ResponseEntity<?> AjoutRoleAUtilisateur(@RequestBody UtilisateurRoleModel model) {
		
		iUtilisateurService.ajouterRoleAUtilisateur(model.getUsername(), model.getLibelle());
			   
		return ResponseEntity.ok().build();
	     
	  }   
	  
	@GetMapping("/current_user") 
	public Utilisateur consulterSonProfile(Principal principal) {
		 
		return((Utilisateur) this.iUtilisateurService.rechercherUtilisateur(principal.getName()));
		
	}
	
	@GetMapping("/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String bearerToken = request.getHeader(JWTConstUtil.AUTH_HEADER);
		
		if(bearerToken!=null && bearerToken.startsWith(JWTConstUtil.PREFIX_BEARER)) {
					
					try {
						String jwt = bearerToken.substring(JWTConstUtil.PREFIX_BEARER.length());
						
						Algorithm algo = Algorithm.HMAC256("secret");
						
						JWTVerifier jwtVerifie = JWT.require(algo).build();
						
						DecodedJWT decodedJWT = jwtVerifie.verify(jwt);
						
						String username = decodedJWT.getSubject();
						
						Utilisateur ut = iUtilisateurService.rechercherUtilisateur(username);
						
						String access_token=JWT.create()
								
								.withSubject(ut.getUsername())
								
								.withExpiresAt(new Date(System.currentTimeMillis()+JWTConstUtil.EXPIRE_TIME_ACCESS_TOKEN))
								
								.withIssuer(request.getRequestURI().toString())
								
								.withClaim("roles", ut.getRoles().stream().map( ga ->ga.getLibelle()).collect(Collectors.toList()))
								
								.sign(algo);
						
						Map<String,String> tokens = new HashMap<>();
						
						tokens.put("access_token", access_token);
						
						tokens.put("refresh_token", jwt);
						
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						
						new ObjectMapper().writeValue(response.getOutputStream(), tokens);
						
					} catch (Exception e) { 
						// TODO: handle exception
						
						response.setHeader("Error message", e.getMessage() );
						
						response.sendError(HttpServletResponse.SC_FORBIDDEN);
					} 
					 
				}else {
					
					throw new RuntimeException("refresh token required");
				}
			}
				
	@PostMapping("/generateToken")
	public void generateToken(@RequestBody Utilisateur utilisateurModel,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Algorithm algo = Algorithm.HMAC256(JWTConstUtil.SECRET); 
		
		String username = utilisateurModel.getUsername();
		
		Utilisateur ut = iUtilisateurService.rechercherUtilisateur(username);
		
		//verifie que l'utilisateur est authentifié
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		utilisateurModel.getUsername(),
                		utilisateurModel.getPassword()
                )
        );
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		
		
		
		//creer son token
		String token=JWT.create()
				
				.withSubject(ut.getUsername()) 
				
				.withExpiresAt(new Date(System.currentTimeMillis()+JWTConstUtil.EXPIRE_TIME_ACCESS_TOKEN))
				
				.withIssuer(request.getRequestURI().toString())
				
				.withClaim("roles", ut.getRoles().stream().map( ga ->ga.getLibelle()).collect(Collectors.toList()))
				
				.sign(algo); 
		
		response.setHeader(JWTConstUtil.AUTH_HEADER, token);
		
		Map<String,Object> tokens = new HashMap<>();
		
		tokens.put("token", token);
		//tokens.put("user",iUtilisateurService.rechercherUtilisateur(authentication.getName()));
		
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	};
	
	
	/* @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request,@RequestBody UserForm userForm) {
    	List<Menu> menus = new ArrayList<>();
        HashMap<String, String> menu = null;
        String nom = "";
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userForm.getUsername(),
                		userForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication); 
        
        //Verifier que le compte a au moins un patient
        //Optional<User> optionalUser = userDao.findByUsername(userForm.getUsername()).orElse(null);
        User user = userDao.findByUsername(userForm.getUsername()).orElse(null);
    	List<UserRole> userRoles = userRoleDao.findByUser(user);
        
        //Vérifier l'origine de la connexion
        String origine = request.getHeader("origin");
        String referer = request.getHeader("referer");
        
       
        
        //Token JWT
        String jwt = tokenProvider.generateToken(authentication,user,userRoles.get(0).getRole().getId());
        
        //Save JWTToken
        /*user.setJwtToken(jwt);
        userDao.save(user);
        
        
        JwtAuthenticationResponse reponse = JwtAuthenticationResponse.builder()
				.accessToken(jwt)
				.username(user.getUsername())
				.nom(nom)
				.numero(user.getNumero())
				.menus(menus)
				.build();
        
        return ResponseEntity.ok(reponse);
    }
// Voici la méthode en question 
	 Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userForm.getUsername(),
                		userForm.getPassword()
                )
        );*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	


class UtilisateurModel{
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
  }
	
}
