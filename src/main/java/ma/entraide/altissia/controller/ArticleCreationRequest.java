package ma.entraide.altissia.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ma.entraide.altissia.entity.Article;

public class ArticleCreationRequest {
	
	    public ArticleCreationRequest() {
		super();
	}
		public ArticleCreationRequest(Article article, List<MultipartFile> attachments, List<Long> idDelegations) {
		super();
		this.article = article;
		this.attachments = attachments;
		this.idDelegations = idDelegations;
	}
		public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<MultipartFile> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<MultipartFile> attachments) {
		this.attachments = attachments;
	}
	public List<Long> getIdDelegations() {
		return idDelegations;
	}
	public void setIdDelegations(List<Long> idDelegations) {
		this.idDelegations = idDelegations;
	}
		private Article article;
	    private List<MultipartFile> attachments;
	    private List<Long> idDelegations;
	    // Add other necessary fields

	    // Getters and setters
	}

