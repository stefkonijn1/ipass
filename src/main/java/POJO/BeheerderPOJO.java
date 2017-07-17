package POJO;

public class BeheerderPOJO {
	   private int id;
	   private String naam;
	   private String Achternaam;
	   private int leeftijd;
	   private int clubid;
	   
	   public BeheerderPOJO(){
			
	   }
//	   De constructor om een beheerderobject aan te maken
	   public BeheerderPOJO(int id, String naam, String Achternaam, int leeftijd, int clubid){
	   	this.id = id;
	   	this.naam = naam;
	   	this.Achternaam = Achternaam;
	   	this.leeftijd = leeftijd;
	   	this.clubid = clubid;
	   }

//	   De getters en setters
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
		return Achternaam;
	}
	public void setAchternaam(String achternaam) {
		Achternaam = achternaam;
	}
	public int getLeeftijd() {
		return leeftijd;
	}
	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}
	public int getClubid() {
		return clubid;
	}
	public void setClubid(int clubid) {
		this.clubid = clubid;
	};
}
