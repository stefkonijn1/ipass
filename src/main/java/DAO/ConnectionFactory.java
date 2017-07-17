package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
//	De gegevens van de database
	private static final String DB_DRIV = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require";
	private static final String DB_USER = "ylagedltuploci";
	private static final String DB_PASS = "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(DB_DRIV).newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
// De connectie aanmaken
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
