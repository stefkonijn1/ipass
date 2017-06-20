package nl.hu.v1ipass.test.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.mysql.cj.api.mysqla.result.Resultset;

public class Codes {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public void AddTeam(String naam, int klasse) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            

            // Statements allow to issue SQL queries to the database
          preparedStatement = connect.prepareStatement("INSERT INTO TEAMS(teamnaam, klasse, gespeelde_wedstrijden,gewonnen,gelijk,verloren,punten, doelpunten_v, doelpunten_t, club_id) VALUES(?, ?, 0,0,0,0,0,0,0, 1)");
        preparedStatement.setString(1, naam);
        preparedStatement.setInt(2, klasse);

          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
            connect.close();
        }

    }
    public void DeleteTeam(int id) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            

            // Statements allow to issue SQL queries to the database
          preparedStatement = connect.prepareStatement("DELETE FROM TEAMS WHERE TEAMCODE = ?;");
        preparedStatement.setInt(1, id);

          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public void AddLid(String naam, String Achternaam, int leeftijd, int teamcode, String pasw) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            

            // Statements allow to issue SQL queries to the database
          preparedStatement = connect.prepareStatement("INSERT INTO Leden(Naam, Achternaam, Leeftijd, teamcode, password) VALUES(?, ?, ?, ?, ?)");
        preparedStatement.setString(1, naam);
        preparedStatement.setString(2, Achternaam);
        preparedStatement.setInt(3, leeftijd);
        preparedStatement.setInt(4, teamcode);
        preparedStatement.setString(5, pasw);




          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public void DeleteLid(int id) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            

            // Statements allow to issue SQL queries to the database
          preparedStatement = connect.prepareStatement("DELETE FROM LEDEN WHERE Lid_id = ?;");
        preparedStatement.setInt(1, id);

          preparedStatement.executeUpdate();
          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    public void UitslagDoorgevenProg(int thuis, int ronde, int dpthuis, int dpuit) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            

            // Statements allow to issue SQL queries to the database
          preparedStatement = connect.prepareStatement("UPDATE PROGRAMMA SET Doelpunten_T = ?, Doelpunten_U = ? WHERE Thuisploeg = ? AND datum = ?;");
        preparedStatement.setInt(1, dpthuis);
        preparedStatement.setInt(2, dpuit);
        preparedStatement.setInt(3, thuis);
        preparedStatement.setInt(4, ronde);
        preparedStatement.executeUpdate();
        

          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public void UitslagDoorgevenTeamsThuis(int thuis, int ronde, int dpthuis, int dpuit) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
        	int winnaar = 0;
            if (dpthuis>dpuit){
            	winnaar = 1;
            }
            else if (dpuit>dpthuis){
            	winnaar = 2;
            }
            else if (dpthuis == dpuit){
            	winnaar = 3;
            }
            
int gespeeld = getGespeeld(thuis);
int gewonnen = getGewonnen(thuis);
int gelijk = getGelijk(thuis);
int verloren = getVerloren(thuis);
int punten = getPunten(thuis);
int dpv = getDoelpunten_v(thuis);
int dpt = getDoelpunten_T(thuis);
preparedStatement = connect.prepareStatement("UPDATE TEAMS SET gespeelde_wedstrijden = ?, gewonnen = ?, gelijk = ?, verloren = ?, punten = ?, Doelpunten_V = ?, Doelpunten_T = ? WHERE teamcode = ? ");
gespeeld = gespeeld + 1;
preparedStatement.setInt(1, gespeeld);

if (winnaar == 1){
        	gewonnen = gewonnen + 1;
        	punten = punten + 3;
            preparedStatement.setInt(2, gewonnen);
            preparedStatement.setInt(3, gelijk);
            preparedStatement.setInt(4, verloren);
            preparedStatement.setInt(5, punten);
        }
        else if (winnaar == 2){
        	verloren = verloren + 1;
        	
            preparedStatement.setInt(2, gewonnen);
            preparedStatement.setInt(3, gelijk);
            preparedStatement.setInt(4, verloren);
            preparedStatement.setInt(5, punten);
        }
        else if (winnaar == 3){
        	gelijk = gelijk + 1;
        	punten = punten + 1;
        	
            preparedStatement.setInt(2, gewonnen);
            preparedStatement.setInt(3, gelijk);
            preparedStatement.setInt(4, verloren);
            preparedStatement.setInt(5, punten);
        }
        dpv = dpv + dpthuis;
        dpt = dpt + dpuit;
        preparedStatement.setInt(6, dpv);
        preparedStatement.setInt(7, dpt);
        preparedStatement.setInt(8, thuis);


        preparedStatement.executeUpdate();
        

          System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    public void UitslagDoorgevenTeamsUit(int thuis, int ronde, int dpthuis, int dpuit) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            int winnaar = 0;
            if (dpthuis>dpuit){
            	winnaar = 1;
            }
            else if (dpuit>dpthuis){
            	winnaar = 2;
            }
            else if (dpthuis == dpuit){
            	winnaar = 3;
            }
            

            // Statements allow to issue SQL queries to the database
            int gespeeld = getGespeeld(thuis);
            int gewonnen = getGewonnen(thuis);
            int gelijk = getGelijk(thuis);
            int verloren = getVerloren(thuis);
            int punten = getPunten(thuis);
            int dpv = getDoelpunten_v(thuis);
            int dpt = getDoelpunten_T(thuis);
            preparedStatement = connect.prepareStatement("UPDATE TEAMS SET Gespeelde_wedstrijden = ?, gewonnen = ?, gelijk = ?, verloren = ?, punten = ?, Doelpunten_V = ?, Doelpunten_T = ? WHERE teamcode = ? ");
            gespeeld = gespeeld + 1;

            
            preparedStatement.setInt(1, gespeeld);
            if (winnaar == 1){
                    	verloren = verloren + 1;

                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + dpuit;
                        dpt = dpt + dpthuis;
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, thuis);
                    }
                    else if (winnaar == 2){
                    	gewonnen = gewonnen + 1;
                    	punten = punten + 3;
                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + dpuit;
                        dpt = dpt + dpthuis;
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, thuis);
                    }
                    else if (winnaar == 3){
                    	gelijk = gelijk + 1;
                    	punten = punten + 1;
                    	
                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + dpuit;
                        dpt = dpt + dpthuis;
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, thuis);
                    }



                    preparedStatement.executeUpdate();
                    

                      System.out.println("Gelukt!");


        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    private ResultSet rs12 = null;
    public int getUitploeg(int team, int ronde) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Uitploeg FROM Programma WHERE Thuisploeg = ? and datum = ?;");
            preparedStatement.setInt(1, team);
            preparedStatement.setInt(1, ronde);

            rs12  = preparedStatement.executeQuery();

            int str = 0;
            while (rs12.next()) {
                 str = rs12.getInt("Uitploeg");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    
    private ResultSet rs5 = null;
    public int getPunten(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT punten FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs5  = preparedStatement.executeQuery();

            int str = 0;
            while (rs5.next()) {
                 str = rs5.getInt("Punten");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs6 = null;
    public int getGespeeld(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Gespeelde_wedstrijden FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs6  = preparedStatement.executeQuery();

            int str = 0;
            while (rs6.next()) {
                 str = rs6.getInt("Gespeelde_wedstrijden");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs7 = null;
    public int getGewonnen(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL queryg
            preparedStatement = connect.prepareStatement("SELECT Gewonnen FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs7  = preparedStatement.executeQuery();

            int str = 0;
            while (rs7.next()) {
                 str = rs7.getInt("Gewonnen");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs8 = null;
    public int getGelijk(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Gelijk FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs8  = preparedStatement.executeQuery();

            int str = 0;
            while (rs8.next()) {
                 str = rs8.getInt("Gelijk");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs9 = null;
    public int getVerloren(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Verloren FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs9  = preparedStatement.executeQuery();

            int str = 0;
            while (rs9.next()) {
                 str = rs9.getInt("Verloren");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs10 = null;
    public int getDoelpunten_v(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Doelpunten_V FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs10  = preparedStatement.executeQuery();

            int str = 0;
            while (rs10.next()) {
                 str = rs10.getInt("Doelpunten_V");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs11 = null;
    public int getDoelpunten_T(int team) throws Exception{
    	try {
            // This will load the MySQL driver, each DB has its own driver
    		Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Doelpunten_T FROM Teams WHERE Teamcode = ?;");
            preparedStatement.setInt(1, team);
            rs11  = preparedStatement.executeQuery();

            int str = 0;
            while (rs11.next()) {
                 str = rs11.getInt("Doelpunten_T");
                          }
                   return str;
            
            
//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 

    }
    private ResultSet rs1 = null;
    public ResultSet Stand(int comp) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT * FROM TEAMS WHERE klasse = ? ORDER BY punten DESC;");
            preparedStatement.setInt(1, comp);
            rs1  = preparedStatement.executeQuery();


//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 
		return rs1;

    }
    private ResultSet rs2 = null;
    public ResultSet Prog(int comp, int ronde) throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
        	Class.forName("org.postgresql.Driver");
            // Setup the connection with the DB
        	Connection connect = null;
        	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
            
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            preparedStatement = connect.prepareStatement("SELECT Wedstrijd_id, datum, Thuisploeg, Uitploeg, Doelpunten_T, Doelpunten_U FROM PROGRAMMA WHERE competitie = ? AND Datum = ?;");
            preparedStatement.setInt(1, comp);
            preparedStatement.setInt(2, ronde);

            rs2  = preparedStatement.executeQuery();


//            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        } 
		return rs2;

    }
private ResultSet rs3 = null;

public String naamthuis(int thuis) throws Exception{
	try {
        // This will load the MySQL driver, each DB has its own driver
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	Connection connect = null;
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT Teamnaam FROM Teams WHERE Teamcode = ?;");
        preparedStatement.setInt(1, thuis);
        rs3  = preparedStatement.executeQuery();

        String str = "";
        while (rs3.next()) {
             str = rs3.getString("Teamnaam");
                      }
               return str;
        
        
//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 

}
private ResultSet rs4 = null;

public String naamuit(int uit) throws Exception{
	try {
        // This will load the MySQL driver, each DB has its own driver
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	Connection connect = null;
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT Teamnaam FROM Teams WHERE Teamcode = ?;");
        preparedStatement.setInt(1, uit);
        rs3  = preparedStatement.executeQuery();

        String str = "";
        while (rs3.next()) {
             str = rs3.getString("Teamnaam");
                      }
               return str;
        
        
//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 

}
private ResultSet rs13 = null;

public ResultSet ControleerLid(String user, String pasw) throws Exception{
	try {
        // This will load the MySQL driver, each DB has its own driver
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT naam FROM LEDEN WHERE Password = ?");
        preparedStatement.setString(1, pasw);

        rs13  = preparedStatement.executeQuery();


//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 
	return rs13;

}
private ResultSet rs14 = null;

public ResultSet ControleerClub(String user, String pasw) throws Exception{
	try {
        // This will load the MySQL driver, each DB has its own driver
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	Connection connect = null;
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT Clubnaam FROM CLUBS WHERE Password = ?");
        preparedStatement.setString(1, pasw);

        rs14  = preparedStatement.executeQuery();


//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 
	return rs14;

}
private ResultSet rs15 = null;

public ResultSet ControleerBeheerder(String user, String pasw) throws Exception{
	try {
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	Connection connect = null;
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT Naam FROM Beheerders WHERE Password = ?");
        preparedStatement.setString(1, pasw);

        rs15  = preparedStatement.executeQuery();


//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 
	return rs15;

}
private ResultSet rs16 = null;

public ResultSet GetwwBeheerder() throws Exception{
	try {
        // This will load the MySQL driver, each DB has its own driver
		Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
    	Connection connect = null;
    	connect = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-177-33.eu-west-1.compute.amazonaws.com:5432/d4riu3puptf4ur?sslmode=require","ylagedltuploci", "dd417a43a879a89cfc8759588ecd0688f09b68a1069816399722e8e8c03df79e");
        
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        preparedStatement = connect.prepareStatement("SELECT Password FROM Beheerders ");

        rs16  = preparedStatement.executeQuery();


//        writeResultSet(resultSet);
    } catch (Exception e) {
        throw e;
    } 
	return rs16;

}
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            int id = resultSet.getInt("Teamcode");
            String naam = resultSet.getString("Teamnaam");
            int klasse = resultSet.getInt("Klasse");
            int gesp = resultSet.getInt("Gespeelde_wedstrijden");
            int gew = resultSet.getInt("Gewonnen");
            int gl = resultSet.getInt("Gelijk");
            int vl = resultSet.getInt("Verloren");
            int p = resultSet.getInt("Punten");
            int v = resultSet.getInt("Doelpunten_V");
            int t = resultSet.getInt("Doelpunten_T");
            int club = resultSet.getInt("Club_id");
            System.out.println("ID: " + id);
            System.out.println("Naam: " + naam);
            System.out.println("Klasse: " + klasse);
            System.out.println("Wed: " + gesp);
            System.out.println("Geewonnen: " + gew);
            System.out.println("gl: " + gl);
            System.out.println("vl: " + vl);
            System.out.println("p: " + p);
            System.out.println("v: " + v);
            System.out.println("t: " + t);
            System.out.println("c: " + club);
            

        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}