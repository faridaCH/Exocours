package fr.m2i.medical6spring.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "patient", schema = "medicaldb")
public class PatientEntity {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private int id;
    private VilleEntity ville;
    private String telephone;
   private String email;

    public PatientEntity(int id, String nom, String prenom, Date dateNaissance, String adresse, VilleEntity ville, String email,String telephone) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=dateNaissance;
        this.adresse=adresse;
        this.ville=ville;
        this.email=email;
        this.telephone=telephone;
    }

    public PatientEntity() {

    }



    @Basic
    @Column(name = "telephone", nullable = false)
    public String getTelephone() {
        return telephone;
    }
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setTelephone(String tel) {
        this.telephone = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nom", nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom", nullable = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "date_naissance", nullable = false)
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 200)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(dateNaissance, that.dateNaissance) && Objects.equals(adresse, that.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, dateNaissance, adresse, id);
    }

    @OneToOne
    @JoinColumn(name = "ville", referencedColumnName = "id", nullable = true)
    public VilleEntity getVille() {
        return ville;
    }

    public void setVille(VilleEntity ville) {
        this.ville = ville;
    }
}
