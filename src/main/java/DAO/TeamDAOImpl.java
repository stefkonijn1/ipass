package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.TeamPOJO;

public class TeamDAOImpl implements TeamDAO {
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
	public TeamPOJO findTeam(int teamcode) throws SQLException {
		String queryString = "SELECT * FROM teams WHERE teamcode = " + teamcode;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		TeamPOJO team = new TeamPOJO();
		while (res.next()) {
			team = new TeamPOJO(res.getString("teamnaam"), res.getInt("klasse"));
					}
		
		return team;
    }
		
	@Override
	public void AddTeam(TeamPOJO team) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();

          preparedStatement = connect.prepareStatement("INSERT INTO TEAMS(teamnaam, klasse, gespeelde_wedstrijden,gewonnen,gelijk,verloren,punten, doelpunten_v, doelpunten_t, club_id) VALUES(?, ?, 0,0,0,0,0,0,0, 1)");
        preparedStatement.setString(1, team.getTeamnaam());
        preparedStatement.setInt(2, team.getKlasse());

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
	public void DeleteTeam(int teamcode) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
            

          preparedStatement = connect.prepareStatement("DELETE FROM TEAMS WHERE TEAMCODE = ?;");
        preparedStatement.setInt(1, teamcode);

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
	public void close() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void DeleteTeam(TeamPOJO team) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
