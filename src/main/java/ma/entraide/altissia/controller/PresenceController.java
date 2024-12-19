package ma.entraide.altissia.controller;

import ma.entraide.altissia.service.*;
import ma.entraide.altissia.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presence")
@CrossOrigin("*")
public class PresenceController {

    @Autowired
    private PresenceService etabissementService;


    @GetMapping("/all")
    public ResponseEntity<List<Presence>> getAll() {
        List<Presence> Presences =  etabissementService.getAllPresences();
        return ResponseEntity.ok(Presences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presence> getPresence(@PathVariable Long id) {
        try {
            Presence Presence = etabissementService.getPresenceById(id);
            return ResponseEntity.ok(Presence);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Presence> addBeneficiaire(@RequestBody Presence Presence) {
        try {
            Presence result = etabissementService.createPresence(Presence);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Presence> update(@PathVariable Long id, @RequestBody Presence update) {
        try {
            Presence result = etabissementService.updatePresence(update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}
