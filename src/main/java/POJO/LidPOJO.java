package POJO;

public class LidPOJO {
	
	   private int id;
	   private String naam;
	   private String achternaam;
	   private int leeftijd;
	   private int teamcode;
	   private String pasw;
	   

public LidPOJO(){
	
}
//Constructor voor lid object
public LidPOJO( String naam, String achternaam, int leeftijd, int teamcode, String pasw){
	this.naam = naam;
	this.achternaam = achternaam;
	this.leeftijd = leeftijd;
	this.teamcode = teamcode;
}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public int getLeeftijd() {
		return leeftijd;
	}
	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}
	public int getTeamcode() {
		return teamcode;
	}
	public void setTeamcode(int teamcode) {
		this.teamcode = teamcode;
	}
	public String getPasw() {
		return pasw;
	}
	public void setPasw(String pasw) {
		this.pasw = pasw;
	}
}
