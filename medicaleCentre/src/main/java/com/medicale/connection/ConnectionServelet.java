/**
 * 
 */
package com.medicale.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author chelh
 *
 */
public class ConnectionServelet { /* une seule connection qq soit le nb d'utilisateur */

//	 declarer une connection 
	private static Connection connection;
	static {
//	try catch  en cas de non  chargement de jdbc 
		try {
//	charger le pilote JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");
//			etablir la connection avec la base de données medicalDB
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalDB", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public ConnectionServelet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the connection
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public static void setConnection(Connection connection) {
		ConnectionServelet.connection = connection;
	}

}
