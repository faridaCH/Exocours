package fr.m2i.medical6spring.security;

import fr.m2i.medical6spring.entities.UserEntity;
import fr.m2i.medical6spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //check si le user existe en base donnée
        //UserEntity user = userRepository.findByUsername(username);
        UserEntity user = userRepository.findByUsernameOrEmail(username,username);

        if(user == null){
            throw new UsernameNotFoundException("No user named" + username);
        }else{
            return new UserDetailsImpl(user);
        }

    }
}

