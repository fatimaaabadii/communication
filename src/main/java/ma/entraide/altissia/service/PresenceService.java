package ma.entraide.altissia.service;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.altissia.entity.*;
import ma.entraide.altissia.repository.*;

import java.util.*;

@Service
public class PresenceService {
    
    @Autowired
    private PresenceRepository presenceRepository;


    public Presence createPresence(Presence newPresence){

        return presenceRepository.save(newPresence);
    }

    public Presence updatePresence(Presence newPresence){

        Optional<Presence> updatedPresence = presenceRepository.findById(newPresence.getId());
        
        Presence pre = updatedPresence.get();

      
        pre.setName(newPresence.getName());
      
    
        return presenceRepository.save(pre);
    }

    public Presence getPresenceById(Long PresenceId) {
        return presenceRepository.findById(PresenceId)
                                 .orElseThrow(() -> new NoSuchElementException("Presence not found"));
    }

    public List<Presence> getPresencesById(List<Long> PresenceIds) {
        return presenceRepository.findAllById(PresenceIds);
    }
    
    

    public void deletePartenariatById(Long partId) {
        Optional<Presence> com = presenceRepository.findById(partId);
        if (com != null) {
            presenceRepository.deleteById(partId);
        } else {
            throw new IllegalArgumentException("Presence not found");
        }
    }

	
	public List<Presence> getAllPresences() {
		
		return presenceRepository.findAll();
	}
}
