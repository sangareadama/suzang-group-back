package com.appatam.Suzang_Group_Back.api.pageController;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.service.IContenuDePageService;
import com.appatam.Suzang_Group_Back.service.IPageAccueilService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PageAccueilController { 
	 
	@Autowired
	IPageAccueilService iPageAccueilService;
	
	@Autowired 
	IContenuDePageService iContenuDePageService;
	
	
	//String chemin="C:\\Users\\SANGARE\\ImageAppatam";
	//String chemin="Images_Suzang_appatam";
	
	@Autowired
	ServletContext context;
	 
	
	@GetMapping("/pageAccueil")  
	public ResponseEntity<List<PageAccueil>>listePageAccueil(){
		
		return ResponseEntity.ok().body(iPageAccueilService.ListePageAccueil());
		
	}
	
	@PostMapping("/pageAccueil/save")
	public  ResponseEntity<PageAccueil> enregistrerPageAccueil(@RequestParam("file") MultipartFile file, @RequestParam("pageAccueil") String pa, @RequestParam("contenus") String ctn )throws JsonParseException ,JsonMappingException,Exception {	   
	
		PageAccueil pageAc = new ObjectMapper().readValue(pa, PageAccueil.class);
		
		//ne sert plus a present
		char[] chars = ctn.toCharArray();
		List<Long> contenus= new ArrayList<Long>();
		
		 for (char c : chars) {
			if(c==','||c=='['||c==']') {	
	
				continue;
			}
			String s = Character.toString(c);
			contenus.add(Long.parseLong(s));  
			
		}
		 
		 
		 
		boolean isExit = new File(context.getRealPath("/Images/")).exists();
		
		if(!isExit) {
		
			new File (context.getRealPath("/Images/")).mkdir();
		}
		
		  String newFileName =getAndTransfertImage(file, pageAc);
		  
		  pageAc.setImage(newFileName);
		
		 URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/save").toUriString());
			
		 PageAccueil pageAccueil=  iPageAccueilService.enregistrerPageAccueil(pageAc);
		 
		 // recuperer leS contenuS de page Temporaire et leS metre dans leS vraiS

		 List<ContenuPageTemp> ctnTemp=iContenuDePageService.ListeContenuPageTemp();
		 
		 for (ContenuPageTemp contenuPageTemp : ctnTemp) {
			 
			 ContenuDePage ctp = new ContenuDePage(contenuPageTemp.getLibelle(),
					 								 contenuPageTemp.getDecalageHaut(),contenuPageTemp.getDecalageGauche());
			 
			 ContenuDePage ctpSaved= iContenuDePageService.enregistrerContenuDePage(ctp);
			 
			 iPageAccueilService.ajouterContenuDePageAuBloc(pageAccueil.getId(), ctpSaved.getId());
				
			 iContenuDePageService.supprimerContenuPageTemp(contenuPageTemp);
			 
			
		}
		  
		  return ResponseEntity.created(uri).body(pageAccueil);
		  
	}
	
	@PostMapping("/pageAccueil/supprimer")
	public void SupprimerPageBloc(@RequestBody PageAccueil pageAcc) {
		
		Collection<ContenuDePage> cdp = pageAcc.getContenus();
		for (ContenuDePage contenuDePage : cdp) {
			
			SupprimerContenuDePage(contenuDePage);
		}
		
		iPageAccueilService.supprimerPageAccueil(pageAcc);
	   
	 }
	
	/*public ResponseEntity<PageAccueil> enregistrerPageAccueil(@RequestBody PageAccueil pa) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iPageAccueilService.enregistrerPageAccueil(pa));
	   
	  }*/
	
	
	@GetMapping("/imagePageAccueil/{id}")  
	public byte[] getImagePageAccueil(@PathVariable("id") Long id)throws Exception{
		
		PageAccueil  pa = iPageAccueilService.rechercherPageAccueil(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/"+pa.getImage()))); 

	}  
	
	
	@PostMapping("/pageAccueil/update")
	public ResponseEntity<PageAccueil> modifierPageAccueil(@RequestParam("file") MultipartFile file, @RequestParam("pageAccueil") String pa)throws JsonParseException ,JsonMappingException,Exception {
		
		PageAccueil pageAc = new ObjectMapper().readValue(pa, PageAccueil.class);
		
		System.out.println("------------------> "+file.getOriginalFilename());
		//supprimer l'ancienne image et enregistrer la nouvelle
		if(!file.isEmpty()) {
			supprimerPhoto(pageAc);
			
			String newFileName =getAndTransfertImage(file, pageAc);
			  
			pageAc.setImage(newFileName);
			
		}else {
			
			pageAc.setImage(iPageAccueilService.rechercherPageAccueil(pageAc.getId()).getImage());
		}
		
		 
		
		 PageAccueil pageAccueil= iPageAccueilService.modifierPageAccueil(pageAc);
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/update").toUriString());
		
		return ResponseEntity.created(uri).body(pageAccueil);
	   
	 }
	
	
	private String getAndTransfertImage(MultipartFile file, PageAccueil pageAc) {
		
        String filename= file.getOriginalFilename();
		
		String newFileName ="Page_Accueil"+"_"+pageAc.getId()+"_"+FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		
		File serverFile = new File(context.getRealPath("/Images/" +newFileName));
			
		try {
			  
			org.apache.commons.io.FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			
		}catch (Exception e) {
			// TODO: handle exception  
			e.printStackTrace();
		}
		
		return newFileName;  
		
	}

	
	private void supprimerPhoto(PageAccueil pa) throws IOException {

	File ImageFile = new File(context.getRealPath("/Images/" +pa.getImage()));

	 try {
         Files.deleteIfExists(Paths.get(context.getRealPath("/Images/"+pa.getImage())));
        		 
     }
     catch (NoSuchFileException e) {
         System.out.println(
             "No such file/directory exists");
     }
     catch (DirectoryNotEmptyException e) {
         System.out.println("Directory is not empty.");
     }
     catch (IOException e) {
         System.out.println("Invalid permissions.");
     }

     System.out.println("Deletion successful.");

	
	}
	
	@PostMapping("/pageAccueil/contenus/supprimer")
	public PageAccueil SupprimerContenuDePage(@RequestBody ContenuDePage cdp) {
		
		return iContenuDePageService.supprimerContenuDePage(cdp);
	   
	 }
  
	
	
	
	@PostMapping("/pageAccueil/contenus/addContenuToBloc")
	public ResponseEntity<ContenuDePage> addContenuToBlocPage(@RequestParam("contenuPage") String contenuPage, @RequestParam("selectedBlocs") String selectedBlocs)throws JsonParseException ,JsonMappingException,Exception {
		
		System.out.println("------------------------->"+contenuPage);
		
		PageAccueil pageAc = new ObjectMapper().readValue(selectedBlocs, PageAccueil.class);
		
		//for (String iterable_element : selectedBlocs){	
		//	PageAccueil pageAc = new ObjectMapper().readValue(iterable_element, PageAccueil.class);
		//	System.out.println(":::::::::::::"+pageAc.getNom());  
			  
		//}
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/pageAccueil/contenus/addContenuToBloc").toUriString());
		
		return null;
		
		//return ResponseEntity.created(uri).body(iPageAccueilService.enregistrerContenuDePage(cdp));
	   
	 }
}
