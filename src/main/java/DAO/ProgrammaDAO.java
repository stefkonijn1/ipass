package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.ProgrammaPOJO;
import POJO.TeamPOJO;

public interface ProgrammaDAO {

	Connection getConnection() throws SQLException;


	ArrayList<String> getTeamsFromProgramma(int id) throws SQLException;


	ArrayList<ProgrammaPOJO> findProgramma(int i) throws SQLException;


	ResultSet Prog(ProgrammaPOJO prog) throws Exception;


	ArrayList<Integer> findProgrammaTeam(int i) throws SQLException;


	ResultSet findProgrammaCompetitie(int i) throws SQLException;


	ArrayList<ProgrammaPOJO> findProgramma(ArrayList<Integer> lijst) throws SQLException;


	ResultSet Prog(int comp, int ronde) throws Exception;


	ResultSet Stand(int comp) throws Exception;


	void UitslagDoorgevenProg(ProgrammaPOJO prog) throws Exception;


	int findIdFromNaam(int thuis, int ronde) throws SQLException;
	






}