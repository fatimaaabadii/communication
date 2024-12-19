package ma.entraide.altissia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.entraide.altissia.entity.*;

import java.util.*;
public interface DelegationRepository extends JpaRepository<Delegation, Long>{
    List<Delegation> findBydelegation(String delegation);
}