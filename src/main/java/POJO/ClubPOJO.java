package POJO;

import java.util.ArrayList;


public class ClubPOJO {
	   private int id;
	   private String naam;
	   private int beheerderid;
	   private ArrayList<LidPOJO> leden;
	   private ArrayList<TeamPOJO> teams;


	public ArrayList<LidPOJO> getLeden() {
		return leden;
	}
	public void setLeden(ArrayList<LidPOJO> leden) {
		this.leden = leden;
	}
	public ArrayList<TeamPOJO> getTeams() {
		return teams;
	}
	public void setTeams(ArrayList<TeamPOJO> teams) {
		this.teams = teams;
	}
	
	public ClubPOJO(){
		
	}
	
	public ClubPOJO(int id, String naam, int beheerderid){
		this.id = id;
		this.naam = naam;
		this.beheerderid = beheerderid;
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
	public int getBeheerderid() {
		return beheerderid;
	}
	public void setBeheerderid(int beheerderid) {
		this.beheerderid = beheerderid;
	}
}
