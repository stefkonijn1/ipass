package POJO;

import java.util.ArrayList;


public class ProgrammaPOJO {
	
	   private int id;
	   private int ronde;
	   private int thuis;
	   private int uit;
	   private int doelpuntenthuis;
	   private int doelpuntenuit;
	   private int competitie;
		private ArrayList<String> teams;

	   
	   public ArrayList<String> getTeams() {
			return teams;
		}
		public void setTeams(ArrayList<String> teams) {
			this.teams = teams;
		}
		
	public ProgrammaPOJO(){
			
	   }
//	Constructor voor programma object
	   public ProgrammaPOJO( int ronde,int thuis, int uit, int doelpuntenthuis,int doelpuntenuit, int competitie){
	   	this.ronde = ronde;
	   	this.thuis = thuis;
	   	this.uit = uit;
	   	this.doelpuntenthuis = doelpuntenthuis;
	   	this.doelpuntenuit = doelpuntenuit;
	   	this.competitie = competitie;
	   	

	   }
	   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRonde() {
		return ronde;
	}
	public void setRonde(int ronde) {
		this.ronde = ronde;
	}
	
	public int getThuis() {
		return thuis;
	}
	public void setThuis(int thuis) {
		this.thuis = thuis;
	}
	public int getUit() {
		return uit;
	}
	public void setUit(int uit) {
		this.uit = uit;
	}
	public int getDoelpuntenthuis() {
		return doelpuntenthuis;
	}
	public void setDoelpuntenthuis(int doelpuntenthuis) {
		this.doelpuntenthuis = doelpuntenthuis;
	}
	public int getDoelpuntenuit() {
		return doelpuntenuit;
	}
	public void setDoelpuntenuit(int doelpuntentuit) {
		this.doelpuntenuit = doelpuntentuit;
	}
	public int getCompetitie() {
		return competitie;
	}
	public void setCompetitie(int competitie) {
		this.competitie = competitie;
	}
	@Override
	public String toString() {
		return "ProgrammaPOJO [id=" + id + ", ronde=" + ronde + ", thuis=" + thuis + ", uit=" + uit
				+ ", doelpuntenthuis=" + doelpuntenthuis + ", doelpuntenuit=" + doelpuntenuit + ", competitie="
				+ competitie + ", teams=" + teams + "]";
	}

}
