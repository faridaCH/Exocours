package fr.m2i.medical6spring.api;


import fr.m2i.medical6spring.entities.PatientEntity;


import fr.m2i.medical6spring.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InvalidObjectException;
import java.net.URI;
import java.sql.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/patient")
public class PatientAPIController {
    // declarer l'independance
    private PatientService ps;

    // injection des dependances
    public PatientAPIController(PatientService ps) {
        this.ps = ps;
    }

    @GetMapping(value = "", produces = "application/json")
    public Iterable<PatientEntity> getAll() {
        return ps.getAll();
    }

    // http://localhost:8080/api/patient/1
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PatientEntity> get(@PathVariable int id) {
        try {
            PatientEntity p = ps.findPatient(id);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<PatientEntity> add(@RequestBody PatientEntity p) {
        System.out.println(p);
        try {
            ps.addPatient(p);
            // création de l'url d'accès au nouvel objet => http://localhost:8080/api/ville/20
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
            return ResponseEntity.created(uri).body(p);
        } catch (InvalidObjectException e) {
            //return ResponseEntity.badRequest().build();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public void update(@PathVariable int id, @RequestBody PatientEntity p) {
        try {
            ps.editPatient(id, p);

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient introuvable");

        } catch (InvalidObjectException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id)throws Exception {
        // je verifie si le patient existe avant de le supprimer
        try {
            ps.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
