package ma.entraide.altissia.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@AllArgsConstructor
@Entity
public class Article {
	
	 @Column(nullable = false, columnDefinition = "varchar(255)")
	private String etat;
	 
	 @Column(nullable = false, columnDefinition = "varchar(255)")
		private String observation;
	 

	 public String getCorrige() {
		return corrige;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setCorrige(String corrige) {
		this.corrige = corrige;
	}



	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String corrige;
	 
    public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateSoumission() {
		return dateSoumission;
	}

	public void setDateSoumission(String dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	
	

	/*public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}*/

	public Set<Delegation> getDelegations() {
		return delegations;
	}

	public void setDelegations(Set<Delegation> delegations) {
		this.delegations = delegations;
	}



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    
    
   private String dateSoumission;
   
   private String typeEvenet;
   
   private String dateEvent;

   
   public String getDateEvent() {
	return dateEvent;
}

public void setDateEvent(String dateEvent) {
	this.dateEvent = dateEvent;
}

public String getTypeEvenet() {
	return typeEvenet;
}

public void setTypeEvenet(String typeEvenet) {
	this.typeEvenet = typeEvenet;
}

public List<Long> getIdDelegations() {
	return idDelegations;
}

public void setIdDelegations(List<Long> idDelegations) {
	this.idDelegations = idDelegations;
}





   public Article() {
	super();
	// TODO Auto-generated constructor stub
}




@Transient
   private List<Long> idDelegations;
    

   private Date dateCreation;

    public Article(String etat, String observation, String corrige, Long id, String title, String content,
		String dateSoumission, String typeEvenet, String dateEvent, List<Long> idDelegations, Date dateCreation,
		Date dateModification, Set<Delegation> delegations, String sousType, Set<Presence> presences,
		Set<Partenaire> partenaires, List<CelebrationDetails> celebrationDetails) {
	super();
	this.etat = etat;
	this.observation = observation;
	this.corrige = corrige;
	this.id = id;
	this.title = title;
	this.content = content;
	this.dateSoumission = dateSoumission;
	this.typeEvenet = typeEvenet;
	this.dateEvent = dateEvent;
	this.idDelegations = idDelegations;
	this.dateCreation = dateCreation;
	this.dateModification = dateModification;
	this.delegations = delegations;
	this.sousType = sousType;
	this.presences = presences;
	this.partenaires = partenaires;
	this.celebrationDetails = celebrationDetails;
}

	public Date getDateCreation() {
	return dateCreation;
}

public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}

public Date getDateModification() {
	return dateModification;
}

public void setDateModification(Date dateModification) {
	this.dateModification = dateModification;
}



	private Date dateModification;
    
    @ManyToMany
    @JoinTable(
        name = "Delegation_Article",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "delegation_id")
    )
    private Set<Delegation> delegations = new HashSet<>();
    // Getters and setters
    
    
    private String sousType;

    @ManyToMany
    @JoinTable(
        name = "Article_Presence",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "presence_id")
    )
    private Set<Presence> presences = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "Article_Partenaire",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "partenaire_id")
    )
    private Set<Partenaire> partenaires = new HashSet<>();

    public String getSousType() {
		return sousType;
	}

	public void setSousType(String sousType) {
		this.sousType = sousType;
	}

	public Set<Presence> getPresences() {
		return presences;
	}

	public void setPresences(Set<Presence> presences) {
		this.presences = presences;
	}

	public Set<Partenaire> getPartenaires() {
		return partenaires;
	}

	public void setPartenaires(Set<Partenaire> partenaires) {
		this.partenaires = partenaires;
	}

	public List<CelebrationDetails> getCelebrationDetails() {
		return celebrationDetails;
	}

	public void setCelebrationDetails(List<CelebrationDetails> celebrationDetails) {
		this.celebrationDetails = celebrationDetails;
	}



	// Liste des types de célébrations
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<CelebrationDetails> celebrationDetails;
}
    /*@ManyToMany
    @JoinTable(
        name = "Attachments_Article",
        joinColumns = @JoinColumn(name = "article_id"),
        inverseJoinColumns = @JoinColumn(name = "attachments_id")
    )
    private Set<Attachment> attachments = new HashSet<>();*/
