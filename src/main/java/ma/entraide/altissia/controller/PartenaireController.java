package ma.entraide.altissia.controller;

import ma.entraide.altissia.service.*;
import ma.entraide.altissia.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partenaire")
@CrossOrigin("*")
public class PartenaireController {

    @Autowired
    private PartenaireService etabissementService;


    @GetMapping("/all")
    public ResponseEntity<List<Partenaire>> getAll() {
        List<Partenaire> Partenaires =  etabissementService.getAllPartenaires();
        return ResponseEntity.ok(Partenaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partenaire> getPartenaire(@PathVariable Long id) {
        try {
            Partenaire Partenaire = etabissementService.getPartenaireById(id);
            return ResponseEntity.ok(Partenaire);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Partenaire> addBeneficiaire(@RequestBody Partenaire Partenaire) {
        try {
            Partenaire result = etabissementService.createPartenaire(Partenaire);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Partenaire> update(@PathVariable Long id, @RequestBody Partenaire update) {
        try {
            Partenaire result = etabissementService.updatePartenaire(update);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
}

