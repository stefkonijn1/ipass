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
import nl.hu.v1ipass.test.servlets.Programma;


public class ProgrammaDAOImpl implements ProgrammaDAO {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    @Override
	public Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
    
    public ProgrammaPOJO findProgrammaFromId(int id) throws SQLException {
		String queryString = "SELECT * FROM programma WHERE wedstrijd_id = " + id;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		ProgrammaPOJO prog = new ProgrammaPOJO();
		while (res.next()) {
			prog = new ProgrammaPOJO(res.getInt("datum"),res.getInt("thuisploeg"),res.getInt("uitploeg"),res.getInt("doelpunten_t"),res.getInt("doelpunten_u"),res.getInt("competitie"));
		}
		System.out.println(prog);
		return prog;
		}
    @Override
	public ArrayList<ProgrammaPOJO> findProgramma(ArrayList<Integer> lijst) throws SQLException {
    	Connection connect = null;
    	connect = getConnection();
    	
		ArrayList<ProgrammaPOJO> progArray = new ArrayList<ProgrammaPOJO>();

    	for(int n : lijst){
    		String queryString = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma WHERE wedstrijd_id = "	+ n;
    		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
    		while (res.next()) {
    			ProgrammaPOJO progpojo = new ProgrammaPOJO(res.getInt("datum"),res.getInt("thuisploeg"),res.getInt("uitploeg"),res.getInt("doelpunten_t"),res.getInt("doelpunten_u"),res.getInt("competitie"));
    			progArray.add(progpojo);
    		}
    	}
		return progArray;
	}

    @Override
	public ArrayList<String> getTeamsFromProgramma(int id) throws SQLException {
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT teams.teamnaam FROM teams, team_programma, programma WHERE team_programma.team = teams.teamcode AND programma.wedstrijd_id = "+id;
				
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		ArrayList<String> ar = new ArrayList<String>();
		while (res.next()) {
			ar.add(res.getString("teamnaam"));
		}
		return ar;
	}
    @Override
	public ArrayList<Integer> findProgrammaTeam(int i) throws SQLException {
    	Connection connect = null;
    	connect = getConnection();
		String queryString = "SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma, team_programma, team WHERE programma.wedstrijd_id = team_programma.wedstrijd_id AND team.teamcode = team_programma.team AND team_programma.team = "	+ i;
		ResultSet res = getConnection().prepareStatement(queryString).executeQuery();
		ArrayList<Integer> progArray = new ArrayList<Integer>();
		while (res.next()) {
			System.out.println(res.getInt("wedstrijd_id"));
			progArray.add(res.getInt("wedstrijd_id"));

		}

		return progArray;

	}
   
    @Override
    public ResultSet Prog(int comp, int ronde) throws Exception {
         ResultSet rs2 = null;

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
    @Override
    public ResultSet Stand(int comp) throws Exception {
        ResultSet rs1 = null;

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

        } catch (Exception e) {
            throw e;
        } finally {
            
        }

    }
    @Override
    public int findIdFromNaam(int thuis, int ronde) throws SQLException {
    	int id = 90;
    	Connection connect = null;
    	connect = getConnection();
		preparedStatement = connect.prepareStatement("SELECT programma.wedstrijd_id, programma.datum, programma.thuisploeg, programma.uitploeg, programma.doelpunten_t, programma.doelpunten_u, programma.competitie FROM programma WHERE thuisploeg = ? AND datum = ?");
		preparedStatement.setInt(1, thuis);
        preparedStatement.setInt(2, ronde);
        ResultSet rs1  = preparedStatement.executeQuery();
        while(rs1.next()){
         id = (rs1.getInt("wedstrijd_id"));
        }
        System.out.println(id);
		return id;
		

    
	}
    @Override
    public void UitslagDoorgevenTeamsThuis(ProgrammaPOJO prog) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
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
        

          System.out.println("Gelukt!");
          connect.close();


        } catch (Exception e) {
            throw e;
        } finally {
        }

    }
    @Override
    public void UitslagDoorgevenTeamsUit(ProgrammaPOJO prog) throws Exception {
        try {
        	Connection connect = null;
        	connect = getConnection();
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
                    

                      System.out.println("Gelukt!");
                      connect.close();


        } catch (Exception e) {
            throw e;
        } finally {
        }

    }
	@Override
	public ArrayList<ProgrammaPOJO> findProgramma(int i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet findProgrammaCompetitie(int i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet Prog(ProgrammaPOJO prog) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
