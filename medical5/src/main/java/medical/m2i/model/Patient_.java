package medical.m2i.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-11-04T12:09:33.908+0100")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Integer> id;
	public static volatile SingularAttribute<Patient, String> nom;
	public static volatile SingularAttribute<Patient, String> prenom;
	public static volatile SingularAttribute<Patient, String> naissance;
	public static volatile SingularAttribute<Patient, String> adresse;
	public static volatile SingularAttribute<Patient, String> pays;
	public static volatile SingularAttribute<Patient, String> ville;
}
