package ma.entraide.altissia.repository;

import ma.entraide.altissia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsConnexionRepo extends JpaRepository<LogsConnexion, Long> {



}
