package fr.m2i.medical6spring.repositories;

import fr.m2i.medical6spring.entities.PatientEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository  extends CrudRepository<PatientEntity,Integer> {


}
