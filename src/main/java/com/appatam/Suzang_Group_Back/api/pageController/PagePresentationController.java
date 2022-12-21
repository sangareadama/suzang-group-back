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
import com.appatam.Suzang_Group_Back.domain.PagePresentation;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.service.IContenuDePageService;
import com.appatam.Suzang_Group_Back.service.IPagePresentationService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PagePresentationController {
	
	@Autowired 
	IContenuDePageService iContenuDePageService;
	
	@Autowired
	IPagePresentationService IPagePresentationService;
	
	@Autowired
	ServletContext context;
	 
	
	@GetMapping("/pagePresentation")  
	public ResponseEntity<List<PagePresentation>>listeBlocsPagePresentation(){
		
		return ResponseEntity.ok().body(IPagePresentationService.ListePagePresentation());
		
	}   
	
	@PostMapping("/pagePresentation/save")
	public  ResponseEntity<PagePresentation> enregistrerPagePresentation(@RequestParam("file") MultipartFile file, @RequestParam("pagePresentation") String pp, @RequestParam("contenus") String ctn )throws JsonParseException ,JsonMappingException,Exception {	   
	
		PagePresentation pagePresentation = new ObjectMapper().readValue(pp, PagePresentation.class);
		 
		boolean isExit = new File(context.getRealPath("/Images/")).exists();
		
		if(!isExit) {
		
			new File (context.getRealPath("/Images/")).mkdir();
		}
		
		  String newFileName =getAndTransfertImage(file, pagePresentation);
		  
		  pagePresentation.setImage(newFileName);
		
		 URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pagePresentation/save").toUriString());
			
		 PagePresentation pagePres =  IPagePresentationService.enregistrerPagePresentation(pagePresentation);
		 
		 System.out.println("--------------------> "+pagePres.getTitre());
		 // recuperer leS contenuS de page Temporaire et leS metre dans leS vraiS
  
		 List<ContenuPageTemp> ctnTemp=iContenuDePageService.ListeContenuPageTemp();
		 
		 for (ContenuPageTemp contenuPageTemp : ctnTemp) {
			 
			 ContenuDePage ctp = new ContenuDePage( contenuPageTemp.getLibelle(),
					 								 contenuPageTemp.getDecalageHaut(),contenuPageTemp.getDecalageGauche());
			 ContenuDePage ctpSaved= iContenuDePageService.enregistrerContenuDePage(ctp);
			 
			 IPagePresentationService.ajouterContenuDePageAuBloc(pagePres.getId(), ctpSaved.getId());
				
			 iContenuDePageService.supprimerContenuPageTemp(contenuPageTemp);
			 
			
		}
		  
		  return ResponseEntity.created(uri).body(pagePres);
		  
	}
	
	@PostMapping("/pagePresentation/supprimer")
	public void SupprimerPageBloc(@RequestBody PagePresentation pagePres) {
		
		Collection<ContenuDePage> cdp = pagePres.getContenus();
		for (ContenuDePage contenuDePage : cdp) {
			
			SupprimerContenuDePage(contenuDePage);
		}
		
		IPagePresentationService.supprimerPagePresentation(pagePres);
	   
	 }
	
	/*public ResponseEntity<PageAccueil> enregistrerPageAccueil(@RequestBody PageAccueil pa) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iPageAccueilService.enregistrerPageAccueil(pa));
	   
	  }*/
	
	
	@GetMapping("/imagePagePresentation/{id}")  
	public byte[] imagePagePresentation(@PathVariable("id") Long id)throws Exception{
		
		PagePresentation  pp = IPagePresentationService.rechercherPagePresentationParId(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/"+pp.getImage()))); 

	}  
	
	
	@PostMapping("/pagePresentation/update")
	public ResponseEntity<PagePresentation> modifierPageAccueil(@RequestParam("file") MultipartFile file,  @RequestParam("pagePresentation") String pp)throws JsonParseException ,JsonMappingException,Exception {
		
		PagePresentation pagePres = new ObjectMapper().readValue(pp, PagePresentation.class);
		
		//supprimer l'ancienne image et enregistrer la nouvelle
		if(!file.isEmpty()) {
			supprimerPhoto(pagePres);
			
			String newFileName =getAndTransfertImage(file, pagePres);
			  
			pagePres.setImage(newFileName);
			
		}else {
			
			pagePres.setImage(IPagePresentationService.rechercherPagePresentationParId(pagePres.getId()).getImage());
		}
		
		 PagePresentation pagePresentation= IPagePresentationService.modifierPagePresentation(pagePres);
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/update").toUriString());
		
		return ResponseEntity.created(uri).body(pagePresentation);
	   
	 }
	
	
	private String getAndTransfertImage(MultipartFile file, PagePresentation pagePresentation) {
		
        String filename= file.getOriginalFilename();
		
		String newFileName ="Page_Presentation"+"_"+pagePresentation.getId()+"_"+FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		
		File serverFile = new File(context.getRealPath("/Images/" +newFileName));
			
		try {
			  
			org.apache.commons.io.FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			
		}catch (Exception e) {
			// TODO: handle exception  
			e.printStackTrace();
		}
		
		return newFileName;  
		
	}

	
	private void supprimerPhoto(PagePresentation pp) throws IOException {

	File ImageFile = new File(context.getRealPath("/Images/" +pp.getImage()));

	 try {
         Files.deleteIfExists(Paths.get(context.getRealPath("/Images/"+pp.getImage())));
        		 
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
	
	@PostMapping("/pagePresentation/contenus/supprimer")
	public PagePresentation SupprimerContenuDePage(@RequestBody ContenuDePage cdp) {
		
		return IPagePresentationService.supprimerContenuDePage(cdp);
	   
	 }
	
	@PostMapping("/pagePresentation/contenus/update")
	public ResponseEntity<PagePresentation> updateContenuDePage(@RequestBody ContenuDePage cdp) {
		
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pagePresentation/contenus/update").toUriString());
		
		PagePresentation pp = IPagePresentationService.modifierContenuDePage(cdp);
		
		return ResponseEntity.created(uri).body(pp);
	   
	 }
	
	@PostMapping("/pagePresentation/contenusReel/save/{id}")
	public ResponseEntity<PagePresentation> EnregistrerContenuDePageReel(@PathVariable("id") Long id,@RequestBody ContenuDePage cdp) {
		
		PagePresentation pa = IPagePresentationService.rechercherPagePresentationParId(id);
		
		ContenuDePage ctpReel= iContenuDePageService.enregistrerContenuDePage(cdp);
		
		IPagePresentationService.ajouterContenuDePageAuBloc(pa.getId(), ctpReel.getId());
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contenusReel/save").toUriString());
		
		return ResponseEntity.created(uri).body(pa);
	   
	 }
	
	@PostMapping("/pagePresentation/contenus/addContenuToBloc")
	public ResponseEntity<ContenuDePage> addContenuToBlocPage(@RequestParam("contenuPage") String contenuPage, @RequestParam("selectedBlocs") String selectedBlocs)throws JsonParseException ,JsonMappingException,Exception {
		
		System.out.println("------------------------->"+contenuPage);
		
		PagePresentation pagePres = new ObjectMapper().readValue(selectedBlocs, PagePresentation.class);
		
		//for (String iterable_element : selectedBlocs){	
		//	PageAccueil pageAc = new ObjectMapper().readValue(iterable_element, PageAccueil.class);
		//	System.out.println(":::::::::::::"+pageAc.getNom());  
			  
		//}
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/pageAccueil/contenus/addContenuToBloc").toUriString());
		
		return null;
		
		//return ResponseEntity.created(uri).body(iPageAccueilService.enregistrerContenuDePage(cdp));
	   
	 }

}
