package Servlets;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.ProgrammaDAOImpl;
import POJO.ProgrammaPOJO;

@WebServlet(urlPatterns = "/Uitslagen.java")

public class Uitslagen extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		ProgrammaDAOImpl progdao = new ProgrammaDAOImpl();
		
//		Gegevens van html page worden opgehaald

 String thuis = req.getParameter("thuis");
 String ronde = req.getParameter("ronde");
 String dpthuis = req.getParameter("dpthuis");
 String dpuit = req.getParameter("dpuit");

 int thuis1 = Integer.parseInt(thuis);
 int ronde1 = Integer.parseInt(ronde);
 int dpthuis1 = Integer.parseInt(dpthuis);
 int dpuit1 = Integer.parseInt(dpuit);
 






 
 

 try {

	 //Er wordt een programma object aangemaakt door middel van de thuisploeg en de speelonde
	  	ProgrammaPOJO programma = progdao.findProgrammaFromId(progdao.findIdFromNaam(thuis1, ronde1));
//	  	De doelpunten worden geset
	  	programma.setDoelpuntenthuis(dpthuis1);
	  	programma.setDoelpuntenuit(dpuit1);
	  	programma.setId(progdao.findIdFromNaam(thuis1, ronde1));
//	  	De uitslag van de wedtrijd wordt doorgegeven aan het programma
	  	progdao.UitslagDoorgevenProg(programma);
//	  	De uitslag van de wedtrijd wordt doorgegeven aan de stand en het thuis en uit team wordt aangepast
		progdao.UitslagDoorgevenTeamsThuis(programma);
		progdao.UitslagDoorgevenTeamsUit(programma);
		} catch (Exception e) {
		e.printStackTrace();
	}
//HTML page wordt aangemaakt

	 PrintWriter out = resp.getWriter();
	 resp.setContentType("text.html");
	 out.println("<!DOCTYPE html>");
	 out.println("<html>");
	 out.println(" <link rel='stylesheet' href='ipass.css'> ");
	 out.println("  <title>Team toevoegen</title>");
	 out.println(" <body>");
	 out.println(" <p><img src='vep1.jpg' alt='vep1' width='700' height='100'></p>");
	 out.println(" <ul>");
	 out.println(" <li><a class='active' href='inlog.html'>Home</a></li>");
	 out.println("  <li><a href='stand.html'>Stand</a></li>");
	 out.println("  <li><a href='prog.html'>Programmma</a></li>");
	 out.println("  <li><a href='uitslag.html'>Uitsag doorgeven</a></li>");
	 out.println("  <li><a href='team1.html'>Team toevoegen</a></li>");
	 out.println("  <li><a href='team2.html'>Team Verwijderen</a></li>");
	 out.println("  <li><a href='lid1.html'>Lid toevoegen</a></li>");
	 out.println("  <li><a href='lid2.html'>Lid Verwijderen</a></li>");
	 out.println("</ul>");
	 out.println(" <h1>Uitslag doorgeven gelukt!</h1>");
	 out.println("<form class='ann', action='inlog.html'>");
	 out.println("    <input type='submit' value='Ga terug' />");



	 out.println(" </body>");
	 out.println("</html>");

	 }}