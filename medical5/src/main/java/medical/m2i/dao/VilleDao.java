package medical.m2i.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import medical.m2i.model.Ville;

public class VilleDao {

	EntityManagerFactory emf;
	EntityManager em;

	public VilleDao() {
		super();
		emf = Persistence.createEntityManagerFactory("medical7");
		em = emf.createEntityManager();

	}

	public int registerVille(Ville ville) throws ClassNotFoundException {
		int id = 0;

		// Récupération d’une transaction
		EntityTransaction tx = em.getTransaction();
		// Début des modifications
		try {
			tx.begin();
			em.persist(ville);
			tx.commit();
			id = ville.getId();
		} catch (Exception e) {

			tx.rollback();
		} finally {
			// em.close();
			// emf.close();
		}
		System.out.println("id du ville : " + id);
		return id;

	}

	public List<Ville> getVilles() throws ClassNotFoundException {
		return em.createQuery("from Ville").getResultList();
	}

	public List<Ville> getVillesByPays(String pays) throws ClassNotFoundException {
		return em.createNamedQuery("Ville.findByPaysName", Ville.class).setParameter("name", pays).getResultList();
	}

	public Ville getVille(int id) {
		// TODO Auto-generated method stub
		return em.find(Ville.class, id);
	}

	public void editVille(int id, String pays, String nom, Integer codepostal) {

		Ville v = em.find(Ville.class, id);
		EntityTransaction tx = em.getTransaction();

		v.setPays(pays);
		v.setNom(nom);
		v.setCode_postal(codepostal);
//		début des modifications 
		try {
			tx.begin();
			em.persist(v);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {

		}

	}

	public void deleteVille(int id) {
		Ville p = em.find(Ville.class, id);
		EntityTransaction tx = em.getTransaction();
		// D�but des modifications
		try {
			tx.begin();
			em.remove(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			// em.close();
			// emf.close();
		}
	}

}
