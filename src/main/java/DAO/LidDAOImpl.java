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
//    Connectie maken met database
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
//    Een lid object aanmaken van een lid met een bepaald id uit de database
    @Override
	public LidPOJO findLid(int id) throws SQLException {
    	Connection connect = getConnection();

		String queryString = "SELECT * FROM leden WHERE id = " + id;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		LidPOJO lid = new LidPOJO();
		while (res.next()) {
			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
			lid.setId(res.getInt("id"));
		}
		connect.close();
		return lid;
    }
    
//    Een lid object aanmaken voor een lid met een bepaalde naam uit de database
    @Override
	public LidPOJO findLidByName(String naam) throws SQLException {
    	Connection connect = getConnection();

		String queryString = "SELECT * FROM leden WHERE naam = " + naam;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		LidPOJO lid = new LidPOJO();
		while (res.next()) {
			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
		}
		connect.close();
		return lid;
    }
    
//    Lid object aanmaken voor een lid met een bepaald password uit de database
    @Override
   	public LidPOJO findLidByPasw(String pasw) throws SQLException {
    	Connection connect = getConnection();

    	
   		String queryString = "SELECT * FROM leden WHERE password = " + pasw;
   		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
   		LidPOJO lid = new LidPOJO();
   		while (res.next()) {
   			lid = new LidPOJO(res.getString("naam"), res.getString("achternaam"), res.getInt("leeftijd"), res.getInt("teamcode"), res.getString("pasw"));
   		}
   		connect.close();
   		return lid;
       }
		
//    Een lid toevoegen aan de database
	@Override
	public void AddLid(LidPOJO lid) throws Exception {
        try {
        	Connection connect = getConnection();


          preparedStatement = connect.prepareStatement("INSERT INTO Leden(Naam, Achternaam, Leeftijd, teamcode, password) VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setString(1, lid.getNaam());
        preparedStatement.setString(2, lid.getAchternaam());
        preparedStatement.setInt(3, lid.getLeeftijd());
        preparedStatement.setInt(4, lid.getTeamcode());
        preparedStatement.setString(5, lid.getPasw());




          preparedStatement.executeUpdate();
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
	
//	Een lid verwijderen uit de database
	@Override
	public void DeleteLid(int id) throws Exception {
        try {
        	Connection connect = getConnection();


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
	
//	Een functie om in te loggen als lid
	public ResultSet ControleerLid(String naam, String pasw) throws Exception{
		ResultSet rs13 = null;
		try {
        	Connection connect = getConnection();

            
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

	
}
