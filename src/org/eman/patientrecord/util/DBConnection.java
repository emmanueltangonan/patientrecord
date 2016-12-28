package org.eman.patientrecord.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private static final String DATASOURCE_CONTEXT = "java:comp/env/jdbc/patientDB";
	
	public static Connection getDBConnection(){
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup(DATASOURCE_CONTEXT);
			if(ds != null) {
				conn = ds.getConnection();
			}
		} catch (NamingException | SQLException ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args){
		Connection conn = getDBConnection();
		if(conn != null){
			System.out.println("Connection successful...");
		}else{
			System.out.println("Error in connecting...");
		}
	}
	
}
