/**
 * 
 */
package com.medicale.bo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medicale.connection.ConnectionServelet;

/**
 * @author chelh
 *
 */
public class Patient {
	private static Integer id = 0;
	private static String nom;
	private static String prenom;
	private static Date dateNaissance;
	private static String adresse;
	private static String pays;
	private static String ville;

	/**
	 * 
	 */
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param adresse
	 * @param pays
	 * @param ville
	 */
	public Patient(String nom, String prenom, Date dateNaissance, String adresse, String pays, String ville) {
		id++;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.pays = pays;
		this.ville = ville;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	public static void addPatient(Patient p) {

		try (Connection connect = ConnectionServelet.getConnection();
				Statement st = connect.createStatement();
				PreparedStatement ps = connect.prepareStatement(
						"INSERT INTO Patient(nom,prenom,dateNaissance,adresse,pays,ville)Values(?,?,?,?,?,?)");) {

			// desactivation de lautocommit
//			connect.setAutoCommit(false);

			ps.setString(1, p.getNom());
			ps.setString(2, p.getPrenom());
			ps.setDate(3, p.getDateNaissance());
			ps.setString(4, p.getAdresse());
			ps.setString(5, p.getPays());
			ps.setString(6, p.getVille());

			ps.executeUpdate();

//			System.out.println("INSERT INTO Patient(nom,prenom,dateNaissance,adresse,pays,ville)Values('" + p.getNom()
//					+ "','" + p.getPrenom() + "','" + p.getDateNaissance() + "','" + p.getAdresse() + "','"
//					+ p.getPays() + "','" + p.getVille() + "');");
//			System.out.println(ps);
//		PreparedStatement ps = connect.prepareStatement("INSERT INTO Patient(nom,prenom,dateNaissance,adresse,pays,ville)"
//				+ "Values('"+ p.getNom() + "','" + p.getPrenom() + "','" + p.getDateNaissance() + "','" + p.getAdresse()
//							+ "','" + p.getPays() + "','" + p.getVille() + "');");
//			executer le requete
//			st.executeUpdate(sql);
//			ps.executeUpdate();
//			ps.executeQuery();
			connect.commit();
//			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Patient> findAllPatient() {
		List<Patient> patientsBD = new ArrayList<Patient>();
		String sql = null;

		// connection à la base de données
		try (Connection connect = ConnectionServelet.getConnection(); Statement st = connect.createStatement();) {

			// desactivation de lautocommit
			connect.setAutoCommit(false);

			// Construction de la requete d'insertion
			sql = "SELECT * FROM patient;";
			// execuyion de la requête
			ResultSet resultSet = st.executeQuery(sql);
			// recuperation des resultats
			while (resultSet.next()) {
				Patient pat = new Patient(resultSet.getString("nom"), resultSet.getString("prenom"),
						Date.valueOf(resultSet.getString("dateNaissance")), resultSet.getString("adresse"),
						resultSet.getString("pays"), resultSet.getString("ville"));
				patientsBD.add(pat);
			}
//			connect.commit();
//			connect.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patientsBD;

	}

}
