package ma.entraide.altissia.service;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.altissia.entity.*;
import ma.entraide.altissia.repository.*;

import java.util.*;

@Service
public class PartenaireService {
    
    @Autowired
    private PartenaireRepository PartenaireRepository;


    public Partenaire createPartenaire(Partenaire newPartenaire){

        return PartenaireRepository.save(newPartenaire);
    }

    public Partenaire updatePartenaire(Partenaire newPartenaire){

        Optional<Partenaire> updatedPartenaire = PartenaireRepository.findById(newPartenaire.getId());
        
        Partenaire pre = updatedPartenaire.get();

      
        pre.setName(newPartenaire.getName());
      
    
        return PartenaireRepository.save(pre);
    }

    public Partenaire getPartenaireById(Long PartenaireId) {
        return PartenaireRepository.findById(PartenaireId)
                                 .orElseThrow(() -> new NoSuchElementException("Partenaire not found"));
    }

    public List<Partenaire> getPartenairesById(List<Long> PartenaireIds) {
        return PartenaireRepository.findAllById(PartenaireIds);
    }
    
    

    public void deletePartenariatById(Long partId) {
        Optional<Partenaire> com = PartenaireRepository.findById(partId);
        if (com != null) {
            PartenaireRepository.deleteById(partId);
        } else {
            throw new IllegalArgumentException("Partenaire not found");
        }
    }

	
	public List<Partenaire> getAllPartenaires() {
		
		return PartenaireRepository.findAll();
	}
}
