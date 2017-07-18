package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import POJO.ProgrammaPOJO;
import POJO.TeamPOJO;
import Servlets.Programma;


public class ProgrammaDAOImpl implements ProgrammaDAO {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
//    Connectie maken met de database
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
//    Een programma object aanmaken voor een programma met een bepaald id uit de database
	ProgrammaPOJO prog = null;

    @Override
    public ProgrammaPOJO findProgrammaFromId(int id) throws SQLException {
    	try{
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT * FROM programma WHERE wedstrijd_id = " + id;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		 prog = new ProgrammaPOJO();
		while (res.next()) {
			prog = new ProgrammaPOJO(res.getInt("datum"),res.getInt("thuisploeg"),res.getInt("uitploeg"),res.getInt("doelpunten_t"),res.getInt("doelpunten_u"),res.getInt("competitie"));
		}
        connect.close();
    	} catch (Exception e) {
	        throw e;
	    } 
        return prog;
		}
    
//    Er wordt een lijst met programma objecten aangemaakt door middel van een lijst met wedstrijd id's die worden omgezet naar volledige wedstrijden
	ArrayList<ProgrammaPOJO> progArray = null;

    @Override
	public ArrayList<ProgrammaPOJO> findProgramma(ArrayList<Integer> lijst) throws SQLException {
    	try{
    	Connection connect = null;
    	connect = getConnection();
    	
		 progArray = new ArrayList<ProgrammaPOJO>();

    	for(int n : lijst){
    		String queryString = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma WHERE wedstrijd_id = "	+ n;
    		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
    		while (res.next()) {
    			ProgrammaPOJO progpojo = new ProgrammaPOJO(res.getInt("datum"),res.getInt("thuisploeg"),res.getInt("uitploeg"),res.getInt("doelpunten_t"),res.getInt("doelpunten_u"),res.getInt("competitie"));
    			progArray.add(progpojo);
    		}
    	}
        connect.close();
        } catch (Exception e) {
	        throw e;
	    } 
		return progArray;
	}

//    Er wordt een lijst gemaakt met de teams die in een bepaald programma zitten
	ArrayList<String> ar = null;

    @Override
	public ArrayList<String> getTeamsFromProgramma(int id) throws SQLException {

    	try{
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT teams.teamnaam FROM teams, team_programma, programma WHERE team_programma.team = teams.teamcode AND programma.wedstrijd_id = "+id;
				
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		 ar = new ArrayList<String>();
		while (res.next()) {
			ar.add(res.getString("teamnaam"));
		}
        connect.close();
    	 } catch (Exception e) {
 	        throw e;
 	    } 
		return ar;
	}
    
//    Er wordt een lijst gemaakt met wedstrijd id's van een bepaald team
	ArrayList<Integer> progArray1 = null;

    @Override
	public ArrayList<Integer> findProgrammaTeam(int i) throws SQLException {

    	try{
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma, team_programma, team WHERE programma.wedstrijd_id = team_programma.wedstrijd_id AND team.teamcode = team_programma.team AND team_programma.team = "	+ i;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		 progArray1 = new ArrayList<Integer>();
		while (res.next()) {
			System.out.println(res.getInt("wedstrijd_id"));
			progArray1.add(res.getInt("wedstrijd_id"));

		}
        connect.close();
    	 } catch (Exception e) {
  	        throw e;
  	    } 
		return progArray1;

	}
   
//    Het programma wordt hier aangemaakt door middel van een speelronde en een competitie
    ResultSet rs2 = null;

    @Override
    public ResultSet Prog(int comp, int ronde) throws Exception {

        try {
        	Connection connect = null;
        	connect = getConnection();
        	
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT Wedstrijd_id, datum, Thuisploeg, Uitploeg, Doelpunten_T, Doelpunten_U FROM PROGRAMMA WHERE competitie = ? AND Datum = ?;");
            preparedStatement.setInt(1, comp);
            preparedStatement.setInt(2, ronde);

            rs2  = preparedStatement.executeQuery();

	        connect.close();
	       
        } catch (Exception e) {
  	        throw e;
  	    } 
		return rs2;

    }
    
//    Functie om de stand te laten zien van een bepaalde competitie
    ResultSet rs1 = null;

    @Override
    public ResultSet Stand(int comp) throws Exception {

        try {
        	Connection connect = null;
        	connect = getConnection();
        	
            statement = connect.createStatement();
            preparedStatement = connect.prepareStatement("SELECT * FROM TEAMS WHERE klasse = ? ORDER BY punten DESC;");
            preparedStatement.setInt(1, comp);
            rs1  = preparedStatement.executeQuery();

	        connect.close();
	       
        } catch (Exception e) {
  	        throw e;
  	    } 
		return rs1;

    }
    
//    Funcite om de uitslag van een wedstrijd door te geven aan het programma
    @Override
    public void UitslagDoorgevenProg(ProgrammaPOJO prog) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
        	
        preparedStatement = connect.prepareStatement("UPDATE PROGRAMMA SET Doelpunten_T = ?, Doelpunten_U = ? WHERE wedstrijd_id = ?;");
        preparedStatement.setInt(1, prog.getDoelpuntenthuis());
        preparedStatement.setInt(2, prog.getDoelpuntenuit());
        preparedStatement.setInt(3, prog.getId());
        preparedStatement.executeUpdate();
   
        connect.close();
        statement.close();
        preparedStatement.close();
        } catch (Exception e) {
  	        throw e;
  	    } finally {
              close();
          }

    }
    
//    Functie om het id van een westrijd op te vragen door middel van de thuisspelende ploeg en de speelronde
	int id = 90;

    @Override
    public int findIdFromNaam(int thuis, int ronde) throws SQLException { 

    	try{
    	Connection connect = null;
    	connect = getConnection();
		preparedStatement = connect.prepareStatement("SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma WHERE thuisploeg = ? AND datum = ?");
		preparedStatement.setInt(1, thuis);
        preparedStatement.setInt(2, ronde);
        ResultSet rs1  = preparedStatement.executeQuery();
        while(rs1.next()){
         id = (rs1.getInt("wedstrijd_id"));
        }
        connect.close();
    	} catch (Exception e) {
  	        throw e;
  	    } 
        return id;
		

    
	}
    
//    Functie om de uitslag van een wedstrijd te verwerken in de stand van de thuis spelende ploeg
    @Override
    public void UitslagDoorgevenTeamsThuis(ProgrammaPOJO prog) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
//        	Hier wordt de winnaar van de wedstrijd bepaald door te kijken wie er meer gescoord heeft
        	int winnaar = 0;
            if (prog.getDoelpuntenthuis()>prog.getDoelpuntenuit()){
            	winnaar = 1;
            }
            else if (prog.getDoelpuntenuit()>prog.getDoelpuntenthuis()){
            	winnaar = 2;
            }
            else if (prog.getDoelpuntenthuis() == prog.getDoelpuntenuit()){
            	winnaar = 3;
            }
TeamDAOImpl teamdao = new TeamDAOImpl();
//thuis

// Hier wordt het thuisteam aangemaakt als object
TeamPOJO team = teamdao.findTeam(prog.getThuis());
System.out.println(team.toString());
System.out.println(team.getTeamcode());
System.out.println("tste");
int gespeeld = team.getGespeeld();
System.out.println(gespeeld);
int gewonnen = team.getGewonnen();
int gelijk = team.getGelijk();
int verloren = team.getVerloren();
int punten = team.getPunten();
System.out.println(punten);
int dpv = team.getDoelpuntenvoor();
int dpt = team.getDoelpuntentegen();
preparedStatement = connect.prepareStatement("UPDATE TEAMS SET gespeelde_wedstrijden = ?, gewonnen = ?, gelijk = ?, verloren = ?, punten = ?, Doelpunten_V = ?, Doelpunten_T = ? WHERE teamcode = ? ");
gespeeld = gespeeld + 1;
preparedStatement.setInt(1, gespeeld);
// Hier wordt berekend hoeveel punten de thuisploeg krijgt en wat er gebeurt met het doelsalde
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
        dpv = dpv + prog.getDoelpuntenthuis();
        dpt = dpt + prog.getDoelpuntenuit();
        preparedStatement.setInt(6, dpv);
        preparedStatement.setInt(7, dpt);
        preparedStatement.setInt(8, team.getTeamcode());


        preparedStatement.executeUpdate();
        

        connect.close();
        } catch (Exception e) {
  	        throw e;
  	    } finally {
              close();
          }

    }
    
// Functie om de uitslag van de uitploeg te verwerken in de stans van de uitploeg
    @Override
    public void UitslagDoorgevenTeamsUit(ProgrammaPOJO prog) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
//        	Hier wordt de winnaar weer bepaald
            int winnaar = 0;
            if (prog.getDoelpuntenthuis()>prog.getDoelpuntenuit()){
            	winnaar = 1;
            }
            else if (prog.getDoelpuntenuit()>prog.getDoelpuntenthuis()){
            	winnaar = 2;
            }
            else if (prog.getDoelpuntenthuis() == prog.getDoelpuntenuit()){
            	winnaar = 3;
            }
            

            TeamDAOImpl teamdao = new TeamDAOImpl();
          //thuis

// Hier wordt van het uitspelende team een object gemaakt
          TeamPOJO team = teamdao.findTeam(prog.getUit());
          System.out.println("tste");
          int gespeeld = team.getGespeeld();
          System.out.println(gespeeld);
          int gewonnen = team.getGewonnen();
          int gelijk = team.getGelijk();
          int verloren = team.getVerloren();
          int punten = team.getPunten();
          System.out.println(punten);
          int dpv = team.getDoelpuntenvoor();
          int dpt = team.getDoelpuntentegen();
            preparedStatement = connect.prepareStatement("UPDATE TEAMS SET Gespeelde_wedstrijden = ?, gewonnen = ?, gelijk = ?, verloren = ?, punten = ?, Doelpunten_V = ?, Doelpunten_T = ? WHERE teamcode = ? ");
            gespeeld = gespeeld + 1;

//            Hier worden de punten berekend
            preparedStatement.setInt(1, gespeeld);
            if (winnaar == 1){
                    	verloren = verloren + 1;

                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + prog.getDoelpuntenuit();
                        dpt = dpt + prog.getDoelpuntenthuis();
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, team.getTeamcode());
                    }
                    else if (winnaar == 2){
                    	gewonnen = gewonnen + 1;
                    	punten = punten + 3;
                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + prog.getDoelpuntenuit();
                        dpt = dpt + prog.getDoelpuntenthuis();
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, team.getTeamcode());
                    }
                    else if (winnaar == 3){
                    	gelijk = gelijk + 1;
                    	punten = punten + 1;
                    	
                        preparedStatement.setInt(2, gewonnen);
                        preparedStatement.setInt(3, gelijk);
                        preparedStatement.setInt(4, verloren);
                        preparedStatement.setInt(5, punten);
                        dpv = dpv + prog.getDoelpuntenuit();
                        dpt = dpt + prog.getDoelpuntenthuis();
                        preparedStatement.setInt(6, dpv);
                        preparedStatement.setInt(7, dpt);
                        preparedStatement.setInt(8, team.getTeamcode());
                    }



                    preparedStatement.executeUpdate();
                    

        	        connect.close();
        	        

        } catch (Exception e) {
  	        throw e;
  	    } finally {
              close();
          }
    }
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            
        } catch (Exception e) {

        }
    }
}
