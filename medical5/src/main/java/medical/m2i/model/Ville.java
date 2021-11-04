package medical.m2i.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Ville.findByPaysName", query = "SELECT v FROM Ville v WHERE v.pays = :name"), })
public class Ville implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String nom;

	@Column(nullable = false, length = 150)
	private Integer codePostal;

	@Column(nullable = false, length = 150)
	private String pays;

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Ville() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nom
	 * @param code_postal
	 */
	public Ville(Integer id, String nom, Integer code_postal) {
		super();
		this.id = id;
		this.nom = nom;
		this.codePostal = code_postal;
	}

	/**
	 * @param nom
	 * @param code_postal
	 * @param pays
	 */
	public Ville(String nom, Integer code_postal, String pays) {
		super();
		this.nom = nom;
		this.codePostal = code_postal;
		this.pays = pays;
	}

	/**
	 * @param id
	 * @param nom
	 * @param code_postal
	 * @param pays
	 */
	public Ville(Integer id, String nom, Integer code_postal, String pays) {
		super();
		this.id = id;
		this.nom = nom;
		this.codePostal = code_postal;
		this.pays = pays;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the code_postal
	 */
	public Integer getCode_postal() {
		return codePostal;
	}

	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(Integer code_postal) {
		this.codePostal = code_postal;
	}

//	public void setCode_postal(String codepostal) {
//		// TODO Auto-generated method stub
//		this.code_postal = Integer.parseInt(codepostal);
//	}
}
