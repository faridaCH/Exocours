package medical.m2i.api;


import entities.VilleEntity;
import medical.m2i.dao.DbConncection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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


   private VilleEntity getVille(int id){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");

       VilleEntity v=em.find(VilleEntity.class,id);
       if(v==null){
           throw new WebApplicationException(Response.Status.NOT_FOUND);
       }
       return v;
    }
// ville1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VilleEntity getOne(@PathParam("id") int id){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");

        return getVille(id);
    }
    // ajouter une Ville
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public void addVille(VilleEntity p){
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



    // update une ville
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateVille(@PathParam("id") int id,VilleEntity vParam) {
        VilleEntity v = getVille(id);

        // D�but des modifications
        v.setNom(vParam.getNom());
        v.setCodePostal(vParam.getCodePostal());
        v.setPays(vParam.getPays());
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(v);
            tx.commit();
            System.out.println(" fin de delete user");
        } catch (Exception e) {
            tx.rollback();
        } finally {

        }
    }
    }

