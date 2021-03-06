package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import POJO.LidPOJO;
import POJO.TeamPOJO;

public interface LidDAO {

	Connection getConnection() throws SQLException;
	
	
	



	LidPOJO findLid(int id) throws SQLException;



	void AddLid(LidPOJO lid) throws Exception;






	void DeleteLid(int id) throws Exception;









	LidPOJO findLidByName(String naam) throws SQLException;



	LidPOJO findLidByPasw(String pasw) throws SQLException;



	ResultSet ControleerLid(String naam, String pasw) throws Exception;






}