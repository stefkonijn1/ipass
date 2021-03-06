package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.LidPOJO;

public class ClubDAOImpl implements ClubDAO {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
// Functie om connectie met de database te maken
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
// Funtie om in te loggen als club
	@Override
	public ResultSet ControleerClub(String user, String pasw) throws Exception{
		ResultSet rs14 = null;

		try {
        	Connection connect = getConnection();

            
	        statement = connect.createStatement();
	        preparedStatement = connect.prepareStatement("SELECT Clubnaam FROM CLUBS WHERE Password = ?");
	        preparedStatement.setString(1, pasw);
	
	        rs14  = preparedStatement.executeQuery();
	
	        connect.close();
	
	    } catch (Exception e) {
	        throw e;
	    } 
		return rs14;
	
	}
}
