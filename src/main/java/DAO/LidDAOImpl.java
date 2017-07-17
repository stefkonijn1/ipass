package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.LidPOJO;

public class LidDAOImpl implements LidDAO {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
    
    @Override
	public LidPOJO findLid(int id) throws SQLException {
		String queryString = "SELECT * FROM leden WHERE id = " + id;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		LidPOJO lid = new LidPOJO();
		while (res.next()) {
			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
			lid.setId(res.getInt("id"));
		}
		
		return lid;
    }
    @Override
	public LidPOJO findLidByName(String naam) throws SQLException {
		String queryString = "SELECT * FROM leden WHERE naam = " + naam;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		LidPOJO lid = new LidPOJO();
		while (res.next()) {
			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
		}
		
		return lid;
    }
    @Override
   	public LidPOJO findLidByPasw(String pasw) throws SQLException {
   		String queryString = "SELECT * FROM leden WHERE password = " + pasw;
   		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
   		LidPOJO lid = new LidPOJO();
   		while (res.next()) {
   			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
   		}
   		
   		return lid;
       }
		
	@Override
	public void AddLid(LidPOJO lid) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();

          preparedStatement = connect.prepareStatement("INSERT INTO Leden(Naam, Achternaam, Leeftijd, teamcode, password) VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setString(1, lid.getNaam());
        preparedStatement.setString(2, lid.getAchternaam());
        preparedStatement.setInt(3, lid.getLeeftijd());
        preparedStatement.setInt(4, lid.getTeamcode());
        preparedStatement.setString(5, lid.getPasw());




          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");
          connect.close();


        } catch (Exception e) {
            throw e;
        } finally {
        	if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }        }

    }
	@Override
	public void DeleteLid(int id) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
            

          preparedStatement = connect.prepareStatement("DELETE FROM LEDEN WHERE Lid_id = ?;");
        preparedStatement.setInt(1, id);

          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");
          connect.close();


        } catch (Exception e) {
            throw e;
        } finally {
        	if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
        }

    }
	@Override
	public ResultSet ControleerLid(String naam, String pasw) throws Exception{
		ResultSet rs13 = null;
		try {
			Connection connect = null;
        	connect = getConnection();
            
	        statement = connect.createStatement();
	        preparedStatement = connect.prepareStatement("SELECT naam FROM LEDEN WHERE Password = ?");
	        preparedStatement.setString(1, pasw);

	        rs13  = preparedStatement.executeQuery();

	        connect.close();

	    } catch (Exception e) {
	        throw e;
	    } 
		return rs13;

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void DeleteLid(LidPOJO lid) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
