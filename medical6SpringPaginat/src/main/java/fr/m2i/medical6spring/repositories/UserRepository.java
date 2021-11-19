package fr.m2i.medical6spring.repositories;


import fr.m2i.medical6spring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
public UserEntity findByUsername( String username);
    public UserEntity findByUsernameOrEmail( String username, String email);
/*
// userDetailsServiceImpl:  verif de l'authentif
  //  if( user existe&& password correspond){
     //   &1 créer un objet User: userDEtailsImpl
       //         2 injection de cet objet dans la session
  //  }

 */

// UserDetailsImpl :   add user dans la session

}