package medical.m2i.api;


import entities.UserEntity;
import medical.m2i.dao.DbConncection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserRESTAPI {

    EntityManager em= DbConncection.getInstance();
// user
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<UserEntity> getAll(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        EntityManager   em=emf.createEntityManager();
        List<UserEntity> p=em.createNativeQuery("SELECT * FROM user", UserEntity.class).getResultList();
        return p;
    }
// user1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public UserEntity getOne(@PathParam("id") int id){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");

        return em.find(UserEntity.class,id);
    }
    // ajouter une user
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public void addPatient(UserEntity p){


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
        // supprimer un user

        @DELETE
        @Path("/{id}")
        public void deleteUser(@PathParam("id") int id) {
            UserEntity p = em.find(UserEntity.class, id);
            EntityTransaction tx = em.getTransaction();
            // D�but des modifications
            try {
                tx.begin();
                em.remove(p);
                tx.commit();
                System.out.println(" fin de delete user");
            } catch (Exception e) {
                tx.rollback();
            } finally {

            }
        }

    }

