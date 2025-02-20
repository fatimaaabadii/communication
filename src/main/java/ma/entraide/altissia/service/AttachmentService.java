package ma.entraide.altissia.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;


import ma.entraide.altissia.entity.*;
import ma.entraide.altissia.repository.*;

import java.util.*;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AttachmentService {

	public List<Attachment> getAttachmentsById(List<Long> idDeleg) {
		// TODO Auto-generated method stub
		return null;
	}

	 @Autowired
	    private ArticleRepository articleService;

	 
	    
	 @Autowired 
	    private AttachmentRepository attachmentRepository;
	
	    public Attachment createAttachment(MultipartFile file, Long idarticle) throws Exception{
	    	
	    	
	    	
	    	 //Article existingArticle = articleService.getArticleById(attachment.getIdarticle());
	         //suivie.setPartenariat(existingPartenariat);
	    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        try {
	             if(fileName.contains("..")) {
	                 throw  new Exception("Filename contains invalid path sequence "
	                 + fileName);
	             }

	             Attachment attachment
	                     = new Attachment(fileName,
	                     file.getContentType(),
	                     file.getBytes());
	             Article existingArticle = articleService.getArticleById(idarticle);
	             attachment.setArticle(existingArticle);
	             attachment.setIdarticle(idarticle);
	             
	             return attachmentRepository.save(attachment);

	        } catch (Exception e) {
	             throw new Exception("Could not save File: " + fileName);
	        }/*Article existingArticle = articleService.getArticleById(idArticle);
	        att.setArticle(existingArticle); */
	         
	        
	    }

	    
	    
	    
	    
	    
	    
	    
	   /* public Attachment updateAttachment(Attachment att){

	        Optional<Attachment> updatedattachment = attachmentRepository.findById(att.getId());
	        
	        Attachment attach = updatedattachment.get();

	        attach.setFileName(att.getFileName());
	        attach.setFilePath(att.getFilePath());
	       
	        
	        
	        /*Article art = articleService.getArticleById(idArticle);
		      
	        attach.setArticle(art);*/

	         
	         
	        
	         
	        
	        //del.setDelegationAr(newdelegation.getDelegationAr());
	        //del.setDelegationArr(newdelegation.getDelegationArr());
	        //del.setDelegationFr(newdelegation.getDelegationFr());
	        //del.setIdRegion(newdelegation.getIdRegion());
	        //del.setIdRegionNv(newdelegation.getIdRegionNv());
	    
	    /*  return attachmentRepository.save(attach);*/
	     

	    
	    
	    public Attachment getAttachmentById(String fileId) throws Exception{
	        return attachmentRepository.findById(fileId)
	                                 .orElseThrow(() -> new NoSuchElementException("attachment not found"));
	    }
	    
	    
	    public List<Attachment> getAttachmentsByArticleId(Long article_id) {
	        return attachmentRepository.findByArticleId(article_id);
	    }


	    
	   /* public List<Delegation> getDelegationbyname(String delegation){

	        List<Delegation> delegations = delegationRepository.findBydelegation(delegation);
	        return delegations;
	    }*/
	    
	    public void deleteAttachmentById(String id) {
	        Optional<Attachment> corr = attachmentRepository.findById(id);
	        if (corr != null) {
	        	attachmentRepository.deleteById(id);
	        } else {
	            throw new IllegalArgumentException("att not found");
	        }
	    }}
	    
	   

	   
		/*public List<Attachment> getAllAttachments() {
			
			return attachmentRepository.findAll();
		}}
		
		/*@Autowired
	    private JdbcTemplate jdbcTemplate;

	    public Long getLastAutoIncrementValue() {
	        String sql = "SELECT AUTO_INCREMENT AS next_id " +
	                     "FROM INFORMATION_SCHEMA.TABLES " +
	                     "WHERE TABLE_SCHEMA = 'partenariat' " +
	                     "AND TABLE_NAME = 'partenaire'";
	        Long lastId = jdbcTemplate.queryForObject(sql, Long.class);
	        return lastId != null ? lastId : 0L;
	    }
	    public Long getLastAutoIncrementValue(Attachment atta) {
	        if (atta.getId() == null) {

	            Long newId = getLastAutoIncrementValue();
	            atta.setId(newId);
	        }
	        return atta.getId();
	    }

	    
	    
	    
		public void updateAutoIncrementValue(String tableName, long value) {
	        String sql = "ALTER TABLE " + tableName + " AUTO_INCREMENT = " + value;
	        
	        jdbcTemplate.execute(sql);
	    }
	}*/

