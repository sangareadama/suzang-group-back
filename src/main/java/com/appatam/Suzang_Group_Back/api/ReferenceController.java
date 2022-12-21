package com.appatam.Suzang_Group_Back.api;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.Reference;
import com.appatam.Suzang_Group_Back.service.IReferenceService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReferenceController {
	
	@Autowired
	private IReferenceService iReferenceService;
	
	@Autowired
	ServletContext context;
	
	@GetMapping("/reference")  
	public ResponseEntity<List<Reference>>listeReference(){
		
		return ResponseEntity.ok().body(iReferenceService.ListeReference());
		
	}
	
	@PostMapping("/reference/save")
	public ResponseEntity<Reference> enregistrerReference(@RequestParam("file") MultipartFile file, @RequestParam("Reference") String rf )throws JsonParseException ,JsonMappingException,Exception {
		
		Reference ref = new ObjectMapper().readValue(rf, Reference.class);
		
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
		
		if(!isExit) {
		
			new File (context.getRealPath("/Images/")).mkdir();
		}
		

        String filename= file.getOriginalFilename();
		
		String newFileName ="Reference"+"_"+ref.getNom()+"_"+FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		
		File serverFile = new File(context.getRealPath("/Images/" +newFileName));
			
		try {
			  
			org.apache.commons.io.FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			
		}catch (Exception e) {
			// TODO: handle exception  
			e.printStackTrace();
		}
		  
		  ref.setImage(newFileName);
		
		 URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reference/save").toUriString());
		
		
	  return ResponseEntity.created(uri).body(iReferenceService.enregistrerReference(ref));
	   
	  
	}
	
	@GetMapping("/imageReference/{id}")  
	public byte[] getReference(@PathVariable("id") Long id)throws Exception{
		
		Reference  ref = iReferenceService.rechercherReference(id);
		
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/"+ref.getImage()))); 

	}  
	
	@PostMapping("/reference/supprimer")
	public ResponseEntity<Newsletter> supprimerReference(@RequestBody Reference rf) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reference/supprimer").toUriString());
		
		iReferenceService.supprimerReference(rf);
		
		return null;
		
		
	   
	  }

}
