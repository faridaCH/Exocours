package fr.m2i.medical6spring.api;

import fr.m2i.medical6spring.entities.UserEntity;
import fr.m2i.medical6spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    // declaration de dependancea
    private UserService us;


    // injection des dependances
    public UserAPIController(UserService us) {
        this.us = us;
    }

    @GetMapping(value = "", produces = "application/json")
    public Iterable<UserEntity> getAll() {
        return us.getALL();
    }

    // http://localhost:8080/api/user/1
    @GetMapping(value = "/{id}", produces = "application/json")
    public UserEntity get(@PathVariable int id) {
        return us.findUser(id);
    }

    // http://localhost:8080/api/user/delete/id
    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable int id) {
        us.delete(id);
    }

}
