package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.ProgrammaPOJO;
import POJO.TeamPOJO;
import Servlets.Programma;


public class TeamDAOImpl implements TeamDAO {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
// Connectie met database wordt aangemaakt
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
//    Functie om een teamobject te maken voor een team met een bepaalde teamcode
    @Override
	public TeamPOJO findTeam(int teamcode) throws SQLException {
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT * FROM teams WHERE teamcode = " + teamcode;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		TeamPOJO team = new TeamPOJO();
		while (res.next()) {
			team = new TeamPOJO(res.getString("teamnaam"), res.getInt("klasse"));
			team.setTeamcode(res.getInt("teamcode"));
			team.setGespeeld(res.getInt("gespeelde_wedstrijden"));
			team.setGewonnen(res.getInt("gewonnen"));
			team.setGelijk(res.getInt("gelijk"));
			team.setVerloren(res.getInt("verloren"));
			team.setPunten(res.getInt("punten"));
			team.setDoelpuntenvoor(res.getInt("doelpunten_v"));
			team.setDoelpuntentegen(res.getInt("doelpunten_t"));
			team.setClubid(res.getInt("club_id"));
					}
		String queryString2 = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma, team_programma, teams WHERE programma.wedstrijd_id = team_programma.wedstrijd_id AND teams.teamcode = team_programma.team AND team_programma.team = "	+ teamcode;
		ResultSet res2 = getConnection().prepareStatement(queryString2).executeQuery();
		ArrayList<ProgrammaPOJO> progArray = new ArrayList<ProgrammaPOJO>();
		ProgrammaDAOImpl nd = new ProgrammaDAOImpl();
		while (res2.next()) {
			ProgrammaPOJO prog = new ProgrammaPOJO(res2.getInt("datum"),res2.getInt("thuisploeg"),res2.getInt("uitploeg"),res2.getInt("doelpunten_t"),res2.getInt("doelpunten_u"),res2.getInt("competitie"));
			prog.setTeams(nd.getTeamsFromProgramma(res2.getInt("wedstrijd_id")));
			progArray.add(prog);
		}
		team.setProgramma(progArray);
        connect.close();
        statement.close();
        preparedStatement.close();
		
		return team;
    }
    
//    Functie om het id van een team te krijgen door middel van de naam
    @Override
    public int findIdFromNaam(int naam) throws SQLException {
    	int id = 90;
    	Connection connect = null;
    	connect = getConnection();
		preparedStatement = connect.prepareStatement("SELECT teamcode from teams where teamnaam = ?");
		preparedStatement.setInt(1, naam);
        ResultSet rs1  = preparedStatement.executeQuery();
        while(rs1.next()){
         id = (rs1.getInt("teamcode"));
        }
        connect.close();
        statement.close();
        preparedStatement.close();		return id;
	}
    
//    Functie om een team toe te voegen
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
	        statement.close();
	        preparedStatement.close();

        } catch (Exception e) {
            throw e;
        } 

    }
	
//	Functie om een team te verwijderen
	@Override
	public void DeleteTeam(int teamcode) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
            

          preparedStatement = connect.prepareStatement("DELETE FROM TEAMS WHERE TEAMCODE = ?;");
        preparedStatement.setInt(1, teamcode);

          preparedStatement.executeUpdate();

	        connect.close();
	        statement.close();
	        preparedStatement.close();
        } catch (Exception e) {
            throw e;
        } 

    }
	
//	Functie om de naam van het thuisteam op te halen door middel van het id 
	@Override
	public String naamthuis(int thuis) throws Exception{
		 ResultSet rs3 = null;

			try {
				 
				Connection connect = null;
		    	connect = getConnection(); 
	        statement = connect.createStatement();
	        preparedStatement = connect.prepareStatement("SELECT Teamnaam FROM Teams WHERE Teamcode = ?;");
	        preparedStatement.setInt(1, thuis);
	        rs3  = preparedStatement.executeQuery();
	        connect.close();

	        String str = "";
	        while (rs3.next()) {
	             str = rs3.getString("Teamnaam");
	                      }
	        connect.close();
	        statement.close();
	        preparedStatement.close();
	               return str;
	        
	        
	    } catch (Exception e) {
	        throw e;
	    } 

	}
	
//	Functie om de naam van het uitteam te halen door middel van hte id
	@Override
	public String naamuit(int uit) throws Exception{
		
		 ResultSet rs3 = null;

		try {
			 
			Connection connect = null;
	    	connect = getConnection();

	        statement = connect.createStatement();
	        preparedStatement = connect.prepareStatement("SELECT Teamnaam FROM Teams WHERE Teamcode = ?;");
	        preparedStatement.setInt(1, uit);
	        rs3  = preparedStatement.executeQuery();
	        connect.close();

	        String str = "";
	        while (rs3.next()) {
	             str = rs3.getString("Teamnaam");
	                      }
	        connect.close();
	        statement.close();
	        preparedStatement.close();
	               return str;
	        
	        
	    } catch (Exception e) {
	        throw e;
	    } 
	}
}
