package DAO;
import java.sql.Connection;
import java.sql.SQLException;

import POJO.TeamPOJO;

public interface TeamDAO {

	Connection getConnection() throws SQLException;
	
	TeamPOJO findTeam(int id) throws SQLException;

	void AddTeam(TeamPOJO team) throws Exception;
	
	void DeleteTeam(TeamPOJO team) throws Exception;

	
	void close();

	void DeleteTeam(int teamcode) throws Exception;

	String naamuit(int uit) throws Exception;






}