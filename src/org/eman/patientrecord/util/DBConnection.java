package org.eman.patientrecord.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getDBConnection(){
		URI dbUri = null;
		try {
			dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

		try {
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
