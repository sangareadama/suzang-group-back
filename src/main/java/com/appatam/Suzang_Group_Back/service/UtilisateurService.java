package com.appatam.Suzang_Group_Back.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appatam.Suzang_Group_Back.dao.PageBlocDao;
import com.appatam.Suzang_Group_Back.dao.RoleDao;
import com.appatam.Suzang_Group_Back.dao.UtilisateurDao;
import com.appatam.Suzang_Group_Back.domain.Role;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
public class UtilisateurService implements IUtilisateurService , UserDetailsService{

	
	@Autowired
	private UtilisateurDao utilisateurDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PageBlocDao pageBlocDao;
	
	

	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Utilisateur ut = utilisateurDao.findByUsername(username);
		 
		if(ut==null) { 
			
			throw new UsernameNotFoundException("Utilisateur Non trouvé");
			
		}else {
			System.out.println("Utilisateur trouvé "+ut.getNom());
			
		} 
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		ut.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
		});
		return new User(ut.getUsername(), ut.getPassword(), authorities);
	}
 
	@Override
	public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		
		return utilisateurDao.save(utilisateur);
	}
 
	@Override
	public List<Role> ListeRole() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
	
	@Override
	public Role enregistrerRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.save(role);
	}

	@Override
	public void ajouterRoleAUtilisateur(String username, String libelle) {
		// TODO Auto-generated method stub
		Utilisateur utilisateur = utilisateurDao.findByUsername(username);
		Utilisateur ut = new Utilisateur();
		
		System.out.println("okkkkkkkkkkkk "+ut.getRoles());
		Role role = roleDao.findByLibelle(libelle);
		
		
		
		utilisateur.getRoles().add(role);
	}

	@Override
	public Utilisateur rechercherUtilisateur(String username) {
		// TODO Auto-generated method stub
		return utilisateurDao.findByUsername(username);
	}

	@Override
	public List<Utilisateur> ListeUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurDao.findAll();
	}

	
	@Override
	public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
		
		Utilisateur ut = rechercherUtilisateurParId(utilisateur.getId());
		
		ut.setNom(utilisateur.getNom());
		ut.setPrenom(utilisateur.getPrenom());
		ut.setUsername(utilisateur.getUsername());
		ut.setPassword(passwordEncoder.encode("modify"));	
		
		return utilisateurDao.save(ut);
	}

	@Override
	public Utilisateur rechercherUtilisateurParId(Long id) {
		// TODO Auto-generated method stub
		
		Optional<Utilisateur> ut = utilisateurDao.findById(id);
		
		return ut.get();
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
		utilisateurDao.deleteById(utilisateur.getId());
		
	}
 
	

	
}
