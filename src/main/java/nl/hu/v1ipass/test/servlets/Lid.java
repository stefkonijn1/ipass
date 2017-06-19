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

@WebServlet(urlPatterns = "/Lid.java")

public class Lid extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		
 String user = req.getParameter("user");
 String pasw = req.getParameter("pasw");



 
 
 try {
		ResultSet result = dao.ControleerLid(user, pasw);
		
		while(result.next()){
			String em = result.getString("Naam");
			System.out.println(user);
		    System.out.println(em);
			if (em.equals(user)){
				System.out.println("gelijk");
				
			
				
			

	 

	 PrintWriter out = resp.getWriter();
	 resp.setContentType("text.html");
	 out.println("<!DOCTYPE html>");
	 out.println("<html>");
	 out.println(" <link rel='stylesheet' href='ipass.css'> ");
	 out.println("  <title>Team toevoegen</title>");
	 out.println(" <body>");
	 out.println(" <p><img src='vep1.jpg' alt='vep1' width='700' height='100'></p>");
	 out.println(" <ul>");
	 out.println(" <li><a class='active' href='home_lid.html'>Home</a></li>");
	 out.println("  <li><a href='stand_lid.html'>Stand</a></li>");
	 out.println("  <li><a href='prog_lid.html'>Programmma</a></li>");

	 out.println("</ul>");
	 out.println("<h1>Wel gelukt!</h1>");
	 out.println("<form class='ann', action='home_lid.html'>");
	 out.println("    <input type='submit' value='Ga terug' />");



	 out.println(" </body>");
	 out.println("</html>");
		}}
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
			 out.println("<h1>Niet gelukt!</h1>");
			 out.println("<form class='ann', action='index.html'>");
			 out.println("    <input type='submit' value='Ga terug' />");



			 out.println(" </body>");
			 out.println("</html>");}
 
 
 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 }}