package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BeheerderDAOImpl implements BeheerderDAO {
	private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    private ResultSet rs = null;

    @Override
	public ResultSet ControleerBeheerder(String user, String pasw) throws Exception{
		ResultSet rs = null; 

		try {
			
        	Connection connect = null;
        	connect = getConnection();
        	
	        statement = connect.createStatement();
	        preparedStatement = connect.prepareStatement("SELECT naam FROM beheerders WHERE password = ?");
	        
	        System.out.println(pasw);
	        preparedStatement.setString(1, pasw);

	        rs  = preparedStatement.executeQuery();

	        connect.close();

	    } catch (Exception e) {
	        throw e;
	    } 
		return rs;

	}

}
