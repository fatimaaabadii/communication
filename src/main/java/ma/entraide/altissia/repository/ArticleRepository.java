package ma.entraide.altissia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.entraide.altissia.entity.*;



import java.util.*;
public interface ArticleRepository extends JpaRepository<Article, Long>{
   

	Article getArticleById(Long idArticle);
	
	
	@Query("SELECT title AS titre, COUNT(a.id) AS article_count " +
		       "FROM Article a " +
		       
		       "GROUP BY titre")
		List<Object[]> countArticlesByDelegationAndYear();
		
	
		/*@Query("SELECT d.coordination, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y')) AS year, COUNT(da.article_id) AS article_count " +
			       "FROM delegation_article da " +
			       "JOIN delegation d ON da.delegation_id = d.id " +
			       "JOIN article a ON da.article_id = a.id " +
			       "GROUP BY d.coordination, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y'))")
			List<Object[]> countArticlesByCoordinationAndYear();

			
			
			@Query("SELECT a.typeEvenet, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y')) AS year, COUNT(a.id) AS article_count " +
				       "FROM article a " +
				       "GROUP BY a.typeEvenet, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y'))")
				List<Object[]> countArticlesByTypeEvenetAndYear();
				
				
				
				@Query("SELECT a.etat, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y')) AS year, COUNT(a.id) AS article_count " +
					       "FROM article a " +
					       "GROUP BY a.etat, YEAR(STR_TO_DATE(a.dateSoumission, '%d/%m/%Y'))")
					List<Object[]> countArticlesByEtatAndYear();

			
			*/
			
}



