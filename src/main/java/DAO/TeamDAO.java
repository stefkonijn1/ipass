package DAO;
import java.sql.Connection;
import java.sql.SQLException;

import POJO.TeamPOJO;

public interface TeamDAO {

	Connection getConnection() throws SQLException;
	
	TeamPOJO findTeam(int id) throws SQLException;

	void AddTeam(TeamPOJO team) throws Exception;
	

	

	void DeleteTeam(int teamcode) throws Exception;

	String naamuit(int uit) throws Exception;

	int findIdFromNaam(int naam) throws SQLException;

	String naamthuis(int thuis) throws Exception;






}