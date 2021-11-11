package fr.m2i.medical6spring.repositories;

import fr.m2i.medical6spring.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<UserEntity,Integer> {

}
