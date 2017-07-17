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
import nl.hu.v1ipass.test.servlets.Programma;


public class ProgrammaDAOImpl implements ProgrammaDAO {
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
	public ArrayList<String> getTeamsFromProgramma(int id) throws SQLException {

		String queryString = "SELECT team.teamnaam FROM teams, team_programma, programma WHERE team_programma.team = team.teamcode AND programma.wedstrijd_id = "+id;
				
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		ArrayList<String> ar = new ArrayList<String>();
		while (res.next()) {
			ar.add(res.getString("teamnaam"));
		}
		return ar;
	}
    @Override
	public ArrayList<Integer> findProgramma(int i) throws SQLException {
		String queryString = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma, team_programma, team WHERE programma.wedstrijd_id = team_programma.wedstrijd_id AND team.teamcode = team_programma.team AND team_programma.team = "	+ i;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		ArrayList<Integer> progArray = new ArrayList<Integer>();
		while (res.next()) {
			System.out.println(res.getInt("wedstrijd_id"));
			progArray.add(res.getInt("wedstrijd_id"));

		}

		return progArray;

	}
}
