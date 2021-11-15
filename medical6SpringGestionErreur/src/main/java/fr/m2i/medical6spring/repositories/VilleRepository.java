package fr.m2i.medical6spring.repositories;

import fr.m2i.medical6spring.entities.PatientEntity;
import fr.m2i.medical6spring.entities.VilleEntity;
import org.springframework.data.repository.CrudRepository;

public interface VilleRepository extends CrudRepository<VilleEntity,Integer> {

}
