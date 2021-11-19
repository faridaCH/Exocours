package fr.m2i.medical6spring.controller;


import fr.m2i.medical6spring.entities.VilleEntity;
import fr.m2i.medical6spring.repositories.VilleRepository;
import fr.m2i.medical6spring.service.VilleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/ville")
public class VilleController {

    @Autowired
    private VilleService vservice;
    @Autowired
    VilleRepository villeRepository;
/*  // ****************************************************



     "totalItems": 8,
    "villes": [...],
    "totalPages": 3,
    "currentPage": 1

@Autowired
VilleRepository villeRepository;
    @GetMapping("pages")
    public ResponseEntity<Map<String, Object>> getAllByPage(
            @RequestParam(required = false) String nom,
             @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy

    ) {

        try {
            List<VilleEntity> pages = new ArrayList<VilleEntity>();
            Pageable pageable = PageRequest.of(pageNo, pageSize);
         //   Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("nom").descending());




            Page<VilleEntity> pageVilles;
            if (nom == null)
                pageVilles = villeRepository.findAll(pageable);
            else
                pageVilles = villeRepository.findByTitleContaining(nom, pageable);
            pages = pageVilles.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("pages", pages);
            response.put("currentPage", pageVilles.getNumber());
            response.put("totalItems", pageVilles.getTotalElements());
            response.put("totalPages", pageVilles.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/pages/published")
    public ResponseEntity<Map<String, Object>> findByPublished(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<VilleEntity> villes = new ArrayList<VilleEntity>();
            Pageable paging = PageRequest.of(page, size);

            Page<VilleEntity> pageVille = villeRepository.findByPublished(true, paging);
            villes = pageVille.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("villes", villes);
            response.put("currentPage", pageVille.getNumber());
            response.put("totalItems", pageVille.getTotalElements());
            response.put("totalPages", pageVille.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
//*********************************************************************************

    @GetMapping(value = "")
            /*
               public String list( Model model,HttpServletRequest request
                    ,@RequestParam(name="page", defaultValue="0") int page
                    , @RequestParam(name="size", defaultValue="3")int size){
             */
    public String list(Model model, HttpServletRequest request, @RequestParam(name = "search", defaultValue = "") String search
            , @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "size", defaultValue = "3") int size) {
        // String search = request.getParameter("search");

        //  Iterable<VilleEntity> villes = vservice.findAll(search);
        //  Page<VilleEntity> pageVilles = villeRepository.findAll(PageRequest.of(page, size));model.addAttribute("villes" ,pageVilles );
        Page<VilleEntity> pageVilles = villeRepository
                .findByNomContains(search, PageRequest.of(page, size));
        model.addAttribute("villes", pageVilles);
        model.addAttribute("pageCurrent", page);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);
        model.addAttribute("pages", new int[pageVilles.getTotalPages()]);
        return "ville/list_ville";
    }

    // http://localhost:8080/ville/add
    @GetMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("ville", new VilleEntity());
        return "ville/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost(HttpServletRequest request, Model model) {
        // Récupération des paramètres envoyés en POST
        String nom = request.getParameter("nom");
        int codePostal = Integer.parseInt(request.getParameter("codePostal"));
        String pays = request.getParameter("pays");

        // Préparation de l'entité à sauvegarder
        VilleEntity v = new VilleEntity(nom, codePostal, pays);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try {
            vservice.addVille(v);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("ville", v);
            model.addAttribute("error", e.getMessage());
            return "ville/add_edit";
        }
        return "redirect:/ville?success=true";
    }

    public VilleService getVservice() {
        return vservice;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/edit/{id}")
    public String editGetPost(Model model, @PathVariable int id, HttpServletRequest request) {
        System.out.println("Add Edit Ville" + request.getMethod());

        if (request.getMethod().equals("POST")) {
            // Récupération des paramètres envoyés en POST
            String nom = request.getParameter("nom");
            int codePostal = Integer.parseInt(request.getParameter("codePostal"));
            String pays = request.getParameter("pays");

            // Préparation de l'entité à sauvegarder
            VilleEntity v = new VilleEntity(nom, codePostal, pays);

            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try {
                vservice.editVille(id, v);
            } catch (Exception e) {
                v.setId(-1); // hack
                System.out.println(e.getMessage());
                model.addAttribute("ville", v);
                model.addAttribute("error", e.getMessage());
                return "ville/add_edit";
            }
            return "redirect:/ville?success=true";
        } else {
            try {
                model.addAttribute("ville", vservice.findVille(id));
            } catch (NoSuchElementException e) {
                return "redirect:/ville?error=Ville%20introuvalble";
            }

            return "ville/add_edit";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        String message = "?success=true";
        try {
            vservice.delete(id);
        } catch (Exception e) {
            message = "?error=Ville%20introuvalble";
        }
        return "redirect:/ville" + message;
    }

    public void setVservice(VilleService vservice) {
        this.vservice = vservice;
    }
}