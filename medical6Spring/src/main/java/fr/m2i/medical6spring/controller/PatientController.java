package fr.m2i.medical6spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // pour dire que c'est un contorller
@RequestMapping("/patient")   // equivalent  au path
public class PatientController {
// url : http://localhost:8080/patient/test
    @RequestMapping("/test")
        @ResponseBody
        public String testMe(){
            return "<h1> Bonjour </h1>";
        }

}
