package ma.entraide.altissia.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.entraide.altissia.entity.*;



import java.util.*;
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
    // Requêtes personnalisées pour Presence si nécessaire
}