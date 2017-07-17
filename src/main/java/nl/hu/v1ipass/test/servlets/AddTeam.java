package nl.hu.v1ipass.test.servlets;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.LidDAOImpl;
import DAO.TeamDAOImpl;
import POJO.LidPOJO;
import POJO.TeamPOJO;
import nl.hu.v1ipass.test.servlets.Codes;

@WebServlet(urlPatterns = "/AddTeam.java")

public class AddTeam extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		TeamDAOImpl teamdao = new TeamDAOImpl();

		
 String naam = req.getParameter("team");
 String klasse = req.getParameter("klasse");
 
 int klasse1 = Integer.parseInt(klasse);
 
 TeamPOJO team = new TeamPOJO(naam, klasse1);


 
 
 try {
	teamdao.AddTeam(team);
} catch (Exception e) {
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
 out.println(" <h1>Team toevoegen gelukt!</h1>");
 out.println("<form class='ann', action='inlog.html'>");
 out.println("    <input type='submit' value='Ga terug' />");
 out.println(" </body>");
 out.println("</html>");

 }}