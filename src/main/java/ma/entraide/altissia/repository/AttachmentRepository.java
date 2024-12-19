package ma.entraide.altissia.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ma.entraide.altissia.entity.*;

import java.util.*;

public interface AttachmentRepository extends JpaRepository<Attachment, String>{

	void save(Set<Attachment> setatts);

	//List<Attachment> findById(Long idarticle);

	List<Attachment> findByIdarticle(Long idarticle);
	List<Attachment> findByArticleId(Long article_id);

	void deleteByArticleId(Long article_id);
    
}


