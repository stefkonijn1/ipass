package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.TeamPOJO;

public interface ProgrammaDAO {

	Connection getConnection() throws SQLException;


	ArrayList<String> getTeamsFromProgramma(int id) throws SQLException;


	ArrayList<Integer> findProgramma(int i) throws SQLException;
	






}