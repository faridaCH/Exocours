package fr.m2i.medical6spring.api;


import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.service.VilleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InvalidObjectException;
import java.net.URI;
import java.util.NoSuchElementException;

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
    public ResponseEntity<VilleEntity> get(@PathVariable int id) {
        try{
        VilleEntity v = vs.findVille(id);
        return ResponseEntity.ok(v);
    }catch(
    Exception e){
        return ResponseEntity.notFound().build();

    }

}


@PostMapping(value="/{id}", consumes = "application/json")
public ResponseEntity<VilleEntity> add(@RequestBody VilleEntity v){
   System.out.println(v);
   try {
       vs.addVille(v);
       // création de l'url d'accès au nouvel objet => http://localhost:8080/api/ville/20
       URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(v.getId()).toUri();
       return ResponseEntity.created(uri).body(v);
   }catch (InvalidObjectException e){
       //return ResponseEntity.badRequest().build();
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
   }
}

    @PutMapping(value="/{id}" , consumes = "application/json")
    public void update( @PathVariable int id , @RequestBody VilleEntity v ){
        try{
            vs.editVille( id , v );

        }catch ( NoSuchElementException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Ville introuvable" );

        }catch ( InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        // je verifie si la ville existe avant de le supprimer
        try{
            vs.delete(id);
            return ResponseEntity.ok(null);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
