package ma.entraide.altissia.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.altissia.entity.*;
import ma.entraide.altissia.repository.*;

import java.util.*;

@Service
public class ArticleService {
	 @Autowired
	    private ArticleRepository articleRepository;

	 
	    @Autowired 
	    private DelegationService delegationService;
	    
	    @Autowired 
	    private AttachmentRepository attachmentRepository;
	    
	    @Autowired 
	    private AttachmentService attachmentService;
	    
	    @Autowired 
	    private PresenceService presenceService;
	    

	    @Autowired 
	    private PartenaireService partenaireService;
	    
	    

	    public Article createArticle(Article newarticle, List<Long> idDeleg) {
	        if (newarticle == null) {
	            throw new IllegalArgumentException("newarticle cannot be null");
	        }

	        // Vérifier les services et les références de repository
	  

	        // Récupérer les délégations et les définir pour le nouvel article
	        List<Delegation> delegs = delegationService.getDelegationsById(idDeleg);
	        if (delegs == null) {
	            throw new IllegalArgumentException("Delegation list is null");
	        }
	        Set<Delegation> setdelegs = new HashSet<>(delegs);
	        newarticle.setDelegations(setdelegs);
             
	        // Récupérer les pièces jointes et les ajouter à la liste des pièces jointes de l'article
	       
	        newarticle.setDateCreation(new Date());
	        // Enregistrer les pièces jointes dans la base de données
	        // attachmentRepository.saveAll(attachments);
	        if (newarticle.getPresences() != null && !newarticle.getPresences().isEmpty()) {
	        	newarticle.setPresences(new HashSet<>(newarticle.getPresences()));
	        }

	        if (newarticle.getPartenaires() != null && !newarticle.getPartenaires().isEmpty()) {
	        	newarticle.setPartenaires(new HashSet<>(newarticle.getPartenaires()));
	        }

	        if (newarticle.getCelebrationDetails() != null && !newarticle.getCelebrationDetails().isEmpty()) {
	        	newarticle.setCelebrationDetails((newarticle.getCelebrationDetails()));
	        }

	        // Enregistrer l'article dans la base de données
	        return articleRepository.save(newarticle);
	    }

	    public Article updateArticle(Article newArticle, List<Long> idDeleg,List<Long> idPresences, List<Long>idPartenaires){

	        Optional<Article> updatedArticle = articleRepository.findById(newArticle.getId());
	        
	        Article art = updatedArticle.get();

	        art.setTitle(newArticle.getTitle());
	        art.setContent(newArticle.getContent());
	        art.setDateSoumission(newArticle.getDateSoumission());
	        art.setEtat(newArticle.getEtat());
	        art.setCorrige(newArticle.getCorrige());
	        art.setObservation(newArticle.getObservation());
	        art.setTypeEvenet(newArticle.getTypeEvenet());
	        art.setDateEvent(newArticle.getDateEvent());
	        art.setDateModification(new Date());
	        art.setSousType(newArticle.getSousType());
	        List<Delegation> delegs = delegationService.getDelegationsById(idDeleg);
	        Set<Delegation> setdelegs = new HashSet<>(delegs);
	         newArticle.setDelegations(setdelegs);

	         List<Presence> presences = presenceService.getPresencesById(idPresences);
	         Set<Presence> setPresences = new HashSet<>(presences);
	         art.setPresences(setPresences);

	         // Mettre à jour les partenaires
	         List<Partenaire> partenaires = partenaireService.getPartenairesById(idPartenaires);
	         Set<Partenaire> setPartenaires = new HashSet<>(partenaires);
	         art.setPartenaires(setPartenaires);
	         
	       
	         
	         List<CelebrationDetails> newDetails = newArticle.getCelebrationDetails();

	         if (newDetails != null) {
	             // Supprimer les anciens détails qui ne sont pas dans la nouvelle liste
	             art.getCelebrationDetails().removeIf(
	                 oldDetail -> newDetails.stream()
	                     .noneMatch(newDetail -> newDetail.getId() != null && newDetail.getId().equals(oldDetail.getId()))
	             );

	             for (CelebrationDetails newDetail : newDetails) {
	                 if (newDetail.getId() == null) {
	                     // Nouveau détail : ajouter à l'article
	                     newDetail.setArticle(art);
	                     art.getCelebrationDetails().add(newDetail);
	                 } else {
	                     // Détail existant : mettre à jour
	                     CelebrationDetails existingDetail = art.getCelebrationDetails().stream()
	                         .filter(detail -> detail.getId().equals(newDetail.getId()))
	                         .findFirst()
	                         .orElse(null);

	                     if (existingDetail != null) {
	                         // Mettre à jour les propriétés nécessaires
	                         existingDetail.setCount(newDetail.getCount());
	                         existingDetail.setNbrePresence(newDetail.getNbrePresence());
	                         //existingDetail.setAdditionalInfo(newDetail.getAdditionalInfo()); // Exemple de propriété
	                         // Ajouter d'autres propriétés si nécessaire
	                     }
	                 }
	             }
	         }

	         
	         
	    
	    
	        return articleRepository.save(art);
	    }

	    public Article getArticleById(Long articleId) {
	        return articleRepository.findById(articleId)
	                                 .orElseThrow(() -> new NoSuchElementException("article not found"));
	    }

	    
	    
	   /* public List<Delegation> getDelegationbyname(String delegation){

	        List<Delegation> delegations = delegationRepository.findBydelegation(delegation);
	        return delegations;
	    }*/

	      public void deleteArticleById(Long id) {
	    	
	    	
	    	Article article = articleRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Partenariat non trouvé avec l'ID : " + id));
	    	  attachmentRepository.deleteByArticleId(id);
	          // correctionRepository.deleteByArticleId(id);
	        	articleRepository.deleteById(id);
	        	
	        	
	       
	    }

		
		public List<Article> getAllArticles() {
			
			return articleRepository.findAll();
		}
		
		
		public Object dashboard() {
		    Object data = new Object() {
		        public List<Object[]> articlesByDelegationAndYear = articleRepository.countArticlesByDelegationAndYear();
		       /* public List<Object[]> articlesByCoordinationAndYear = articleRepository.countArticlesByCoordinationAndYear();
		        public List<Object[]> articlesByTypeEvenetAndYear = articleRepository.countArticlesByTypeEvenetAndYear();
		        public List<Object[]> articlesByEtatAndYear = articleRepository.countArticlesByEtatAndYear();*/
		    };
		    return data;
		}
	}

