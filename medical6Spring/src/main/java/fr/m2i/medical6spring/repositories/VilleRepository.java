package fr.m2i.medical6spring.repositories;

import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.entities.VilleEntity;
import org.springframework.data.repository.CrudRepository;

public interface VilleRepository extends CrudRepository<VilleEntity,Integer> {
/* Iterable<PatientEntity> findAllByNom(String nom); // equiv a select * from patient where nom=:nom
    Iterable<PatientEntity> findAllByNomAndPrenom(String nom, String prenom); // equiv a select * from patient where nom=:nom and prenom=:prenom

 */
    /*EXO:
    Créer les API de gestion des villes :
    GET ALL / GET By Id et DELETE en utilisant
     un RestController Spring avec les services et les repositories
     */

}
