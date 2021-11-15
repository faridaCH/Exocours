package fr.m2i.medical6spring.service;

import fr.m2i.medical6spring.api.VilleAPIController;
import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.repositories.PatientRepository;
import fr.m2i.medical6spring.repositories.VilleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.InvalidObjectException;
import java.sql.Date;
import java.util.NoSuchElementException;

@Service
public class PatientService {
    private VilleService vs;
    private PatientRepository pr;

    public PatientService(PatientRepository pr) {
        this.pr = pr;
    }

    public Iterable<PatientEntity> getAll(){
        return pr.findAll();
    }

    private void checkPatient( PatientEntity p ) throws InvalidObjectException {

        if( p.getNom().length() <= 2  ){
            throw new InvalidObjectException("Nom  invalide");
        }
        if( p.getPrenom().length() <= 2  ){
            throw new InvalidObjectException("Prenom invalide");
        }
        if( p.getAdresse().length() <= 10  ){
            throw new InvalidObjectException("Adresse invalide");
        }
        /*
        if(VilleAPIController.vs.get(p.getVille().getId()){
            throw new InvalidObjectException(" ville invalide");
        }

check de la date de naissance
        if( p.getDateNaissance().valueOf(????-??-??)){
            throw new InvalidObjectException("Adresse invalide");
        }
 */
    }
    public void addPatient( PatientEntity p ) throws InvalidObjectException {
        checkPatient(p);
        pr.save(p);
    }

    public PatientEntity findPatient( int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return pr.findById(id).get();
    }

    public void delete(int id) {
        // je verifie si le patient existe avant de le supprimer
        pr.deleteById(id);
    }

    public void editPatient( int id , PatientEntity p) throws InvalidObjectException , NoSuchElementException {
        checkPatient(p);
        try{
            PatientEntity pExistante = pr.findById(id).get();


            pExistante.setNom( p.getNom() );
            pExistante.setPrenom( p.getPrenom());
            pExistante.setDateNaissance((p.getDateNaissance()));
            pExistante.setAdresse(p.getAdresse());
            pExistante.setVille(p.getVille());

            pr.save( pExistante );

        }catch ( NoSuchElementException e ){
            throw e;
        }

    }

}
