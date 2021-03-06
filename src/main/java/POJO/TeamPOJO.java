package POJO;

import java.util.ArrayList;


public class TeamPOJO {
	   private int teamcode;
	   private String teamnaam;
	   private int klasse;
	   private int gespeeld;
	   private int gewonnen;
	   private int gelijk;
	   private int verloren;
	   private int punten;
	   private int doelpuntenvoor;
	   private int doelpuntentegen;
	   private int clubid;
		private ArrayList<ProgrammaPOJO> programma;
	   
	   public ArrayList<ProgrammaPOJO> getProgramma() {
			return programma;
		}
		public void setProgramma(ArrayList<ProgrammaPOJO> programma) {
			this.programma = programma;
		}
		
	public TeamPOJO(){
			
	   }
	
//	Constructor voor team objecten
   public TeamPOJO(String teamnaam,int klasse){ 
   	this.teamnaam = teamnaam;
   	this.klasse = klasse;
   	
   }
//Getters en setters
	public int getTeamcode() {
		return teamcode;
	}
	public void setTeamcode(int teamcode) {
		this.teamcode = teamcode;
	}
	public String getTeamnaam() {
		return teamnaam;
	}
	
	public void setTeamnaam(String teamnaam) {
		this.teamnaam = teamnaam;
	}
	public int getKlasse() {
		return klasse;
	}
	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}
	public int getGespeeld() {
		return gespeeld;
	}
	public void setGespeeld(int gespeeld) {
		this.gespeeld = gespeeld;
	}
	public int getGewonnen() {
		return gewonnen;
	}
	public void setGewonnen(int gewonnen) {
		this.gewonnen = gewonnen;
	}
	public int getGelijk() {
		return gelijk;
	}
	public void setGelijk(int gelijk) {
		this.gelijk = gelijk;
	}
	public int getVerloren() {
		return verloren;
	}
	public void setVerloren(int verloren) {
		this.verloren = verloren;
	}
	public int getPunten() {
		return punten;
	}
	public void setPunten(int punten) {
		this.punten = punten;
	}
	public int getDoelpuntenvoor() {
		return doelpuntenvoor;
	}
	public void setDoelpuntenvoor(int doelpuntenvoor) {
		this.doelpuntenvoor = doelpuntenvoor;
	}
	public int getDoelpuntentegen() {
		return doelpuntentegen;
	}
	public void setDoelpuntentegen(int doelpuntentegen) {
		this.doelpuntentegen = doelpuntentegen;
	}
	public int getClubid() {
		return clubid;
	}
	public void setClubid(int clubid) {
		this.clubid = clubid;
	}
	@Override
	public String toString() {
		return "TeamPOJO [teamcode=" + teamcode + ", teamnaam=" + teamnaam + ", klasse=" + klasse + ", gespeeld="
				+ gespeeld + ", gewonnen=" + gewonnen + ", gelijk=" + gelijk + ", verloren=" + verloren + ", punten="
				+ punten + ", doelpuntenvoor=" + doelpuntenvoor + ", doelpuntentegen=" + doelpuntentegen + ", clubid="
				+ clubid + ", programma=" + programma + "]";
	}
}
