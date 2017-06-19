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

@WebServlet(urlPatterns = "/AddLid.java")

public class AddLid extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		
 String naam = req.getParameter("naam");
 String achternaam = req.getParameter("achternaam");
 String leeftijd = req.getParameter("leeftijd");
 String team = req.getParameter("team");
 
 int team1 = Integer.parseInt(team);

 
 
 try {
	dao.AddLid(naam, achternaam, leeftijd, team1);
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
 out.println(" <li><a class='active' href='http://localhost:1555/test/inlog.html'>Home</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/stand.html'>Stand</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/prog.html'>Programmma</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/uitslag.html'>Uitsag doorgeven</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/team1.html'>Team toevoegen</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/team2.html'>Team Verwijderen</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/lid1.html'>Lid toevoegen</a></li>");
 out.println("  <li><a href='http://localhost:1555/test/lid2.html'>Lid Verwijderen</a></li>");
 out.println("</ul>");
 out.println(" <h1>Lid toevoegen gelukt!</h1>");
 out.println("<form class='ann', action='http://localhost:1555/test/inlog.html'>");
 out.println("    <input type='submit' value='Ga terug' />");



 out.println(" </body>");
 out.println("</html>");

 }}