package medical.m2i.api;

import entities.PatientEntity;
import entities.VilleEntity;
import medical.m2i.dao.DbConncection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ville")
public class VilleRESTAPI {

    EntityManager em= DbConncection.getInstance();
// ville
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<VilleEntity> getAll(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        EntityManager   em=emf.createEntityManager();
        List<VilleEntity> p=em.createNativeQuery("SELECT * FROM ville", VilleEntity.class).getResultList();
        return p;
    }
// ville1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VilleEntity getOne(@PathParam("id") int id){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");

        return em.find(VilleEntity.class,id);
    }
    // ajouter une Ville
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public void addPatient(VilleEntity p){


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

    }

