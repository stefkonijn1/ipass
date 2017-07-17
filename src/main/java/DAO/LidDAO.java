package DAO;
import java.sql.Connection;
import java.sql.SQLException;

import POJO.LidPOJO;
import POJO.TeamPOJO;

public interface LidDAO {

	Connection getConnection() throws SQLException;
	
	
	
	void close();



	LidPOJO findLid(int id) throws SQLException;



	void AddLid(LidPOJO lid) throws Exception;



	void DeleteLid(LidPOJO lid) throws Exception;






}