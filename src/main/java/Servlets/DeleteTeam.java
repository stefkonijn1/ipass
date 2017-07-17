package Servlets;
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

@WebServlet(urlPatterns = "/DeleteTeam.java")

public class DeleteTeam extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		TeamDAOImpl teamdao = new TeamDAOImpl();

//		Gegevens van html page worden opgehaald
 String id = req.getParameter("id");
 int id1 = Integer.parseInt(id);

 
 
 try {
//	 team wordt verijderd
	teamdao.DeleteTeam(id1);
} catch (Exception e) {
	e.printStackTrace();
}

// HTML page wordt aangemaakt
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
 out.println(" <h1>Team verwijderen gelukt!</h1>");
 out.println("<form class='ann', action='inlog.html'>");
 out.println("    <input type='submit' value='Ga terug' />");



 out.println(" </body>");
 out.println("</html>");

 }}