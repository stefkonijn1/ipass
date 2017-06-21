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

@WebServlet(urlPatterns = "/Beheerder.java")

public class Beheerder extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		
 String user = req.getParameter("user");
 String pasw = req.getParameter("pasw");


 try {
		ResultSet result = dao.ControleerBeheerder(user, pasw);
		while(result.next()){
			if (result.getString("Naam").equals(user)){
				
			
				
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
	 out.println(" <li><a class='active' href='team1.html'>Team toevoegen</a></li>");
	 out.println("  <li><a href='team2.html'>Team verwijderen</a></li>");
	 out.println("  <li><a href='lid1.html'>Lid toevoegen</a></li>");
	 out.println("  <li><a href='lid2.html'>Lid verwijderen</a></li>");
	 out.println("</ul>");
	 out.println("<h1>U bent nu ingelogd als beheerder!</h1>");
	 out.println("<form class='ann', action='inlog.html'>");
	 out.println("    <input type='submit' value='Ga terug' />");
	 out.println(" </body>");
	 out.println("</html>");
		}
			

 }
 
 PrintWriter out = resp.getWriter();
 resp.setContentType("text.html");
 out.println("<!DOCTYPE html>");
 out.println("<html>");
 out.println(" <link rel='stylesheet' href='ipass.css'> ");
 out.println("  <title>Team toevoegen</title>");
 out.println(" <body>");
 out.println(" <br/><br/><br/><br/>");
 out.println(" <p><img src='vep1.jpg' alt='vep1' width='700' height='100'></p>");
 out.println(" <ul>");
 out.println(" <li><a class='active' href='index.html'>Home</a></li>");
 out.println("</ul>");
 out.println("<h1>Het inoggen is niet gelukt, probeer het opnieuw!</h1>");
 out.println("<form class='ann', action='index.html'>");
 out.println("    <input type='submit' value='Ga terug' />");
 out.println(" </body>");
 out.println("</html>");}
 catch (Exception e) {
		e.printStackTrace();
	}
	 
	 }}