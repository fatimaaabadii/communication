package ma.entraide.altissia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CelebrationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String celebrationType; // Type de célébration (ex : "Présentation", "Fête", etc.)
    private int count; // Nombre de ce type de célébration
    
    private int nbrePresence;

    public int getNbrePresence() {
		return nbrePresence;
	}

	public void setNbrePresence(int nbrePresence) {
		this.nbrePresence = nbrePresence;
	}

	@ManyToOne
    @JoinColumn(name = "article_id")
    @JsonBackReference
    private Article article; // Relation avec l'article

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelebrationType() {
		return celebrationType;
	}

	public void setCelebrationType(String celebrationType) {
		this.celebrationType = celebrationType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Article getArticle() {
		return article;
	}

	public CelebrationDetails(Long id, String celebrationType, int count,int nbrePresence,  Article article) {
		super();
		this.id = id;
		this.celebrationType = celebrationType;
		this.count = count;
		this.nbrePresence = nbrePresence;
		this.article = article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public CelebrationDetails() {
		super();
	}

	
    
    
    
}

