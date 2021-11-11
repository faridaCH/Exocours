package fr.m2i.medical6spring.api;


import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.service.VilleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ville")
public class VilleAPIController {
    // declarer l'independance
    private VilleService vs;

    // injection des dependances
    public VilleAPIController(VilleService vs) {
        this.vs = vs;
    }

    @GetMapping(value = "", produces = "application/json")
    public Iterable<VilleEntity> getAll() {
        return vs.findAll();
    }

    // http://localhost:8080/api/ville/1
    @GetMapping(value = "/{id}", produces = "application/json")
    public VilleEntity get(@PathVariable int id) {
        // PatientEntity pe = new PatientEntity(id, "test", "prenom", Date.valueOf("2021-01-01"), "Adresse test", new VilleEntity());
        return vs.findVille(id);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        // je verifie si la ville existe avant de le supprimer
        vs.delete(id);
    }

}
