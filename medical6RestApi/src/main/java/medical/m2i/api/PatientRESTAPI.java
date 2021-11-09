package medical.m2i.api;

import entities.PatientEntity;
import entities.VilleEntity;
import medical.m2i.dao.DbConncection;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patient")
public class PatientRESTAPI {

    EntityManager em = DbConncection.getInstance();
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<PatientEntity> getAll(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        EntityManager   em=emf.createEntityManager();
        return em.createNativeQuery("SELECT * FROM patient", PatientEntity.class).getResultList();

    }

 */




// patient
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<PatientEntity> getAllByName(@QueryParam("nom") String pnom) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        if (pnom.length() == 0) {
            // p = em.createNativeQuery("SELECT * FROM Patient", PatientEntity.class).getResultList();
        return     em.createNamedQuery("Patient.findAll", PatientEntity.class).getResultList();
        } else {
        return     em.createNamedQuery("Patient.findAllByName", PatientEntity.class).setParameter("nom", "%"+pnom+"%").getResultList();
        }

    }




    // patient 1
    @GET
    @Produces(MediaType.APPLICATION_JSON) // @Produces  car la methode a un parametere de return
    @Path("/{id}")
    public PatientEntity getOne(@PathParam("id") int id) {
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        return em.find(PatientEntity.class, id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)  //  on met @Consumes car la methode prend a un paramettre d'entée
    @Path("")
    public void addPatient(PatientEntity p) {
        // Récupération d’une transaction
        EntityTransaction tx = em.getTransaction();
        // Début des modifications
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
            //   id = patient.getId();
        } catch (Exception e) {

            tx.rollback();
        } finally {
            // em.close();
            // emf.close();
        }

    }

    // supprimer un Patient
    @DELETE
    @Path("/{id}")
    public void deletePatient(@PathParam("id") int id) {
        PatientEntity p = em.find(PatientEntity.class, id);
        // solution de gestion d'exception voir d'autre cas dans ville et user
        if(p==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        //recuperation d'une transaction
        EntityTransaction tx = em.getTransaction();
        // D�but des modifications
        try {
            tx.begin();
            em.remove(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {

        }
    }

    // update d'un Patient
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updatePatient(@PathParam("id") int id, PatientEntity pParam) {
        PatientEntity p = em.find(PatientEntity.class, id);
        // solution de gestion d'exception voir d'autre cas dans ville et user
        if(p==null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {

            p.setNom(pParam.getNom());
            p.setPrenom(pParam.getPrenom());
            p.setDateNaissance(pParam.getDateNaissance());
            p.setAdresse(pParam.getAdresse());
            // cette methode  necessite la modification de tous les paramettre de ville
           // p.setVille(pParam.getVille());   // on fait refresh(p) pour recuperer le reste des données

            VilleEntity v=em.find(VilleEntity.class,pParam.getVille().getId()); // ceci est la bonne methode
            if(v==null){
                //on retourn  not_found qd l'element n'est pas trouvé
                //on retourn  un problem dans le contenu par ex  put pas
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
            p.setVille(v);
        }
        //recuperation d'une transaction
        EntityTransaction tx = em.getTransaction();
        // Début des modifications
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {

        }
       // em.refresh(p);    //pour forcer la récuperation de reste des données
    }
}

