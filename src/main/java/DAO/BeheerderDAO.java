package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BeheerderDAO {

	Connection getConnection() throws SQLException;

	ResultSet ControleerBeheerder(String user, String pasw) throws Exception;

}
