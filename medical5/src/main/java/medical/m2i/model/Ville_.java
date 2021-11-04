package medical.m2i.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-11-04T12:09:33.949+0100")
@StaticMetamodel(Ville.class)
public class Ville_ {
	public static volatile SingularAttribute<Ville, Integer> id;
	public static volatile SingularAttribute<Ville, String> nom;
	public static volatile SingularAttribute<Ville, Integer> code_postal;
	public static volatile SingularAttribute<Ville, String> pays;
}
