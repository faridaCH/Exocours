package fr.m2i.medical6spring.service;


import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.repositories.VilleRepository;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;


@Service
public class VilleService {

    private VilleRepository vr;

    public VilleRepository getVr() {
        return vr;
    }

    public VilleService(VilleRepository vr) {
        this.vr = vr;
    }

    public Iterable<VilleEntity> findAll(){
        return vr.findAll();
    }

    public void checkVille( VilleEntity v ) throws InvalidObjectException {

        if( v.getNom().length() <= 2  ){
            throw new InvalidObjectException("Nom de ville invalide");
        }

        if( v.getPays().length() <= 3  ){
            throw new InvalidObjectException("Nom du pays invalide");
        }

    }


    public void addVille( VilleEntity v ) throws InvalidObjectException {
        checkVille(v);
        vr.save(v);
    }

    public VilleEntity findVille(int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return vr.findById(id).get();
    }

    public void delete(int id) {
        // je verifie si le patient existe avant de le supprimer
        vr.deleteById(id);
    }

    public void editVille( int id , VilleEntity v) throws InvalidObjectException , NoSuchElementException {
        checkVille(v);
        try{
            VilleEntity vExistante = vr.findById(id).get();

            vExistante.setCodePostal( v.getCodePostal() );
            vExistante.setNom( v.getNom() );
            vExistante.setPays( v.getPays() );
            vr.save( vExistante );

        }catch ( NoSuchElementException e ){
            throw e;
        }

    }
}
