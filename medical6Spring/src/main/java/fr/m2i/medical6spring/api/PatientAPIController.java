package fr.m2i.medical6spring.api;


import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.entities.VilleEntity;

import fr.m2i.medical6spring.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/patient")
public class PatientAPIController {
    // declarer l'independance
    private PatientService ps;
    // injection des dependances
    public PatientAPIController(PatientService ps) {
        this.ps = ps;
    }

    @GetMapping(value = "",produces = "application/json")
    public Iterable<PatientEntity> getAll(){
        return ps.getAll();
    }

    // http://localhost:8080/api/patient/1
    @GetMapping(value = "/{id}", produces = "application/json")
    public PatientEntity get(@PathVariable int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return ps.findPatient(id);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        // je verifie si le patient existe avant de le supprimer
        ps.delete(id);
    }

}
