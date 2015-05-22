package nju.iip.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	private static Connection conn = null;
	private static final String DBDRIVER = "com.mysql.jdbc.Driver" ;
 	private static final String DBURL = "jdbc:mysql://114.212.80.14:3306/ecare" ;
	private static final String DBUSER = "root" ;
	private static final String DBPASSWORD = "iip" ;

	public static Connection getConn() {
		try {
			Class.forName(DBDRIVER).newInstance();
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
