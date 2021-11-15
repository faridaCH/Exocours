package fr.m2i.medical6spring.service;
import org.apache.commons.validator.routines.EmailValidator;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientService {
    private PatientRepository pr;
    private VilleRepository vr;

    public PatientService(PatientRepository pr, VilleRepository vr ){
        this.pr = pr;
        this.vr = vr;
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
        System.out.println( "Ville passée en param " + p.getVille().getId() );
        /* try{
            VilleEntity ve = vr.findById(p.getVille().getId()).get();
        }catch( Exception e ){
            throw new InvalidObjectException("Ville invalide");
        } */

        VilleEntity ve = vr.findById(p.getVille().getId()).orElseGet( null );
        if( ve == null ){
            throw new InvalidObjectException("Ville invalide");
        }
        if( !(EmailValidator.getInstance().isValid(p.getEmail()))){
            throw new InvalidObjectException("Email invalide");
        }
        if( !(isValideTel(p.getTel()))){
            throw new InvalidObjectException("Tel invalide");
        }


        //check de la date de naissance
        if( !(isValidDate(p.getDateNaissance()))){
            throw new InvalidObjectException("Date de Naissance  invalide\n  Format date: YYYY-MM-DD ");

        }

    }
    private  boolean isValidDate(Date date) {
      String s=  date.toString();
        String regex = "\\d{4}-\\d{2}-\\d{2}"; //XXXX-XX-XX
        return s.matches(regex);

    }
public boolean isValideTel(String tel){
    String regex = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";

    Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tel);
        return  matcher.matches();

}
    public void addPatient( PatientEntity p ) throws InvalidObjectException {
        checkPatient(p);
        pr.save(p);
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
            pExistante.setTel((p.getTel()));
            pExistante.setEmail(p.getEmail());

            pr.save( pExistante );

        }catch ( NoSuchElementException e ){
            throw e;
        }
        pr.save(p);

    }

}
