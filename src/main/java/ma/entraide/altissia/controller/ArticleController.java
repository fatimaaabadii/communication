package ma.entraide.altissia.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import ma.entraide.altissia.entity.*;
import ma.entraide.altissia.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/article")
public class ArticleController {
	 @Autowired
	    DelegationService delegationService;
	 
	 
	    @Autowired
	    ArticleService articleService;
	    
	    @Autowired
	    AttachmentController attachmentController;

	    @Autowired
	    AttachmentService attachmentService;
	    
	    
	    @PostMapping(value="/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
		 @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<Article> createArticle(@RequestBody Article article
	    		//,
	    		
	    		//@RequestBody List<MultipartFile> attachments
	    		) 
	    		
	    		throws Exception {
	       // Article article = request.getArticle();
	       // List<MultipartFile> attachments = request.getAttachments();
	        List<Long> idDelegations = article.getIdDelegations();
	        // Extract other fields as needed
	        List<Long> idPresences = article.getPresences().stream()
	                .map(Presence::getId)
	                .toList(); // Remplacer `getPresences()` par une méthode adaptée
	        List<Long> idPartenaires = article.getPartenaires().stream()
	                .map(Partenaire::getId)
	                .toList(); 
	        // Process attachments
	        //for (MultipartFile attach : attachments) {
	        // ResponseEntity<List<ResponseData>> createattach = attachmentController.createAttachment(attachments);
	        //}

	        // Create the article
	        Article createdArticle = articleService.createArticle(article, idDelegations);
	        return ResponseEntity.ok().body(createdArticle);
	    }
	        //List<Long> idpartenaires = partenariat.getIdPartenaires();
	        //System.out.println(idpartenaires);
	     
	    
	    
	    
	    @GetMapping("/all")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public List<Article> getAllArticles() {
	        return articleService.getAllArticles();}
	    

	    @Transactional
	    @PutMapping("/update/{id}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
	                                                         @RequestBody Article article)
	                                                         /*@RequestParam List<Long> idDeleg,
	                                                         @RequestParam List<Long> idpartenaire,
	                                                         @RequestParam List<Long> idcommune*/ //)
	                                                         throws Exception {
	    	List<Long> idDeleg = article.getIdDelegations();
	    	List<Long> idPresences = article.getPresences().stream()
	                .map(Presence::getId)
	                .toList(); // Remplacer `getPresences()` par une méthode adaptée
	        List<Long> idPartenaires = article.getPartenaires().stream()
	                .map(Partenaire::getId)
	                .toList(); 
	    	Article updatedArticle = articleService.updateArticle(article, idDeleg /*idpartenaire,*/,idPresences, 
	    			idPartenaires);
	    	return ResponseEntity.ok().body(updatedArticle);
		     //   return ResponseEntity.ok().body(updatedArticle);
	    }

	  //	 List<Long> idDelegs = article.getIdDelegations();
		      //  System.out.println(idDelegs);
		   
		//    List<Long> idAttachments = new ArrayList<>();
		   
	           
	            
	        
		 //   for (Attachment attachment : article.getAttachments()) {
		   //     idAttachments.add(attachment.getId());
		   //     if (attachment.getId() == null) {

	                //Long newId = partenaireService.getLastAutoIncrementValue();
	                //partenaire.setId(newId);
	                //System.out.println(partenaire);
		     //   	Attachment createdPartenaire = attachmentService.createAttachment((MultipartFile) attachment);
		    //    	attachmentService.updateAutoIncrementValue("partenaire", 1);
	        //    } else {
	        //    	Attachment updatedPartenaire = attachmentService.updateAttachment(attachment);
	        //    }
		  //  }
	       // Article updatedArticle = articleService.updateArticle(article, idDelegs, /*idpartenaire,*/idAttachments);
	     //   return ResponseEntity.ok().body(updatedArticle);
	   // }

	    /*@PostMapping("/getpartbyid")
	    public ResponseEntity<Partenariat> getPartenariatById(@RequestBody Long id) {
	        Partenariat partenariat = partenariatService.getPartenariatById(id);
	        return ResponseEntity.ok().body(partenariat);
	    }*/

	    
	   @Transactional
	    @DeleteMapping("/delete/{id}")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public ResponseEntity<?> deleteArticleById(@PathVariable Long id) {
	    
	        articleService.deleteArticleById(id);
	        return ResponseEntity.ok().build();
	    }

	   
	   
	   
	   
	   
	   
	   @GetMapping("/dashboard")
	    public ResponseEntity<Object> getDashboard() {
	        return ResponseEntity.ok(articleService.dashboard());
	    }
}

	   /* @GetMapping("/all")
	    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
	    public List<Article> getAllArticles() {
	        return articleService.getAllArticles();
	    }

	   

	}*/