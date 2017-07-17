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

	ArrayList<Integer> findProgrammaTeam(int i) throws SQLException;

	ArrayList<ProgrammaPOJO> findProgramma(ArrayList<Integer> lijst) throws SQLException;

	ResultSet Prog(int comp, int ronde) throws Exception;

	ResultSet Stand(int comp) throws Exception;

	void UitslagDoorgevenProg(ProgrammaPOJO prog) throws Exception;

	int findIdFromNaam(int thuis, int ronde) throws SQLException;

	void UitslagDoorgevenTeamsThuis(ProgrammaPOJO s) throws Exception;

	void UitslagDoorgevenTeamsUit(ProgrammaPOJO prog) throws Exception;

	ProgrammaPOJO findProgrammaFromId(int id) throws SQLException;
	
}