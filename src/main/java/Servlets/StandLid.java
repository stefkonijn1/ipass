package Servlets;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.ProgrammaDAOImpl;

@WebServlet(urlPatterns = "/StandLid.java")

public class StandLid extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws ServletException, IOException {
					ProgrammaDAOImpl progdao = new ProgrammaDAOImpl();
					
//					Gegevens van html page worden opgehaald

			 String id = req.getParameter("comp");
			 int id1 = Integer.parseInt(id);


			try {
//				De stand wordt opgehaald uit de database
				ResultSet result = progdao.Stand(id1);

			 

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
 out.println(" <h1>De stand van competitie "+ id1 +"</h1>");
 out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"+"<tr> <th>Pl</th> <th>Team</th>  <th>GS</th> <th>WN</th> <th>GL</th> <th>VL</th> <th>DV</th><th>DT</th> <th>PN</th></tr>");
int i = 0;
//De tabel met de stand wordt hier aangemaakt
while(result.next()){
	i=i+1;
out.println("<tr>"
		+ "<td><center>"+i+"</center></td>"
          + "<td><center>"+result.getString("Teamnaam")+"</center></td>"
          		+ "<td><center>"+result.getInt("Gespeelde_wedstrijden")+"</center></td>"
          		+ "<td><center>"+result.getInt("Gewonnen")+"</center></td>"
          		+ "<td><center>"+result.getInt("Gelijk")+"</center></td>"
          		+ "<td><center>"+result.getInt("Verloren")+"</center></td>"
          		+ "<td><center>"+result.getInt("Doelpunten_V")+"</center></td>"
          		+ "<td><center>"+result.getInt("Doelpunten_T")+"</center></td>"
                + "<td><center>"+result.getInt("Punten")+"</center></td>"

          		+ "</tr>");
}
out.println("</table>");
 out.println("<form class='ann', action='home_lid.html'>");
 out.println("    <input type='submit' value='Ga terug' />");



 out.println(" </body>");
 out.println("</html>");
} catch (Exception e) {
	e.printStackTrace();
}
 
 }}