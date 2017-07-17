package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ClubDAO {

	Connection getConnection() throws SQLException;

	ResultSet ControleerClub(String user, String pasw) throws Exception;

}
