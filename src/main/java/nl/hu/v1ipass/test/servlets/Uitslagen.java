package nl.hu.v1ipass.test.servlets;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import nl.hu.v1ipass.test.servlets.Codes;

@WebServlet(urlPatterns = "/Uitslagen.java")

public class Uitslagen extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		
 String thuis = req.getParameter("thuis");
 String ronde = req.getParameter("ronde");
 String dpthuis = req.getParameter("dpthuis");
 String dpuit = req.getParameter("dpuit");

 int thuis1 = Integer.parseInt(thuis);
 int ronde1 = Integer.parseInt(ronde);
 int dpthuis1 = Integer.parseInt(dpthuis);
 int dpuit1 = Integer.parseInt(dpuit);


 
 

 try {
		dao.UitslagDoorgevenProg(thuis1, ronde1, dpthuis1, dpuit1);
		dao.UitslagDoorgevenTeamsThuis(thuis1, ronde1, dpthuis1, dpuit1);
		dao.UitslagDoorgevenTeamsUit(dao.getUitploeg(thuis1), ronde1, dpthuis1, dpuit1);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

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