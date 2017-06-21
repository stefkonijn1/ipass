package nl.hu.v1ipass.test.servlets;

public class ClubPOJO {
	   private int id;
	   private String naam;
	   private int beheerderid;
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
