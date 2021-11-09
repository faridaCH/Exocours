package medical.m2i.api;

import entities.PatientEntity;
import medical.m2i.dao.DbConncection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/patient")
public class PatientRESTAPI {

    EntityManager em = DbConncection.getInstance();

    // patient
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<PatientEntity> getAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        List<PatientEntity> p = em.createNativeQuery("SELECT * FROM patient", PatientEntity.class).getResultList();
        return p;
    }

    // patient 1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public PatientEntity getOne(@PathParam("id") int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        return em.find(PatientEntity.class, id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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
}

