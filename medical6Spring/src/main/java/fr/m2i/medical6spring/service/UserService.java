package fr.m2i.medical6spring.service;


import fr.m2i.medical6spring.entities.UserEntity;
import fr.m2i.medical6spring.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public Iterable<UserEntity> getALL(){
        return ur.findAll();
    }

public UserEntity findUser(int id){
  return ur.findById(id).get();
}
public void delete(int id){
        ur.deleteById(id);
}
}
