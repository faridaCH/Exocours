package fr.m2i.medical6spring.service;


import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.repositories.VilleRepository;
import org.springframework.stereotype.Service;


@Service
public class VilleService {

    private VilleRepository vr;

    public VilleService(VilleRepository vr) {
        this.vr = vr;
    }

    public Iterable<VilleEntity> findAll(){
        return vr.findAll();
    }



    public VilleEntity findVille(int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return vr.findById(id).get();
    }

    public void delete(int id) {
        // je verifie si le patient existe avant de le supprimer
        vr.deleteById(id);
    }
}
