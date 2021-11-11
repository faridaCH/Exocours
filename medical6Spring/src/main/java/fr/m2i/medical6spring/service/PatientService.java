package fr.m2i.medical6spring.service;

import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PatientService {

    private PatientRepository pr;

    public PatientService(PatientRepository pr) {
        this.pr = pr;
    }

    public Iterable<PatientEntity> getAll(){
        return pr.findAll();
    }



    public PatientEntity findPatient( int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return pr.findById(id).get();
    }

    public void delete(int id) {
        // je verifie si le patient existe avant de le supprimer
        pr.deleteById(id);
    }

}
