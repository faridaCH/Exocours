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
/*// user  getAll with @namedQuery
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public List<UserEntity> getAllByNom(@QueryParam("name") String unom)  {
    List<UserEntity> p;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();
    if (unom.length() == 0) {
        //p = em.createNativeQuery("SELECT * FROM User", UserEntity.class).getResultList();
        p = em.createNamedQuery("User.findAll", UserEntity.class).getResultList();
    } else {
        p = em.createNamedQuery("User.findAllByName", UserEntity.class).setParameter("name", unom).getResultList();
    }
    return p;
}

 */



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
    public void addUser(UserEntity p){


            // Récupération d’une transaction
            EntityTransaction tx = em.getTransaction();
            // Début des modifications
            try {
                tx.begin();
                em.persist(p);
                tx.commit();
             //   id = User.getId();
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
                System.out.println("message Error : " +e );
            } finally {

            }
        }

    // supprimer un user

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateUser(@PathParam("id") int id,UserEntity uParam) {
        UserEntity u = em.find(UserEntity.class, id);

        // D�but des modifications
        u.setName(uParam.getName());
        u.setPassword(uParam.getPassword());
        u.setUsername(uParam.getUsername());
        u.setRoles(uParam.getRoles());
        u.setEmail(uParam.getEmail());
        u.setPhotouser(uParam.getPhotouser());

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(u);
            tx.commit();
            System.out.println(" fin de delete user");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("message Error : " +e.getMessage() );
        } finally {

        }
    }

    }

