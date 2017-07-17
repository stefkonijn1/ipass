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
import DAO.TeamDAOImpl;

@WebServlet(urlPatterns = "/Programma.java")

public class Programma extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		ProgrammaDAOImpl progdao = new ProgrammaDAOImpl();
		TeamDAOImpl teamdao = new TeamDAOImpl();
		
//		Gegevens van html page worden opgehaald

 String comp = req.getParameter("comp");
 String ronde = req.getParameter("ronde");

 int comp1 = Integer.parseInt(comp);
 int ronde1 = Integer.parseInt(ronde);


try {
//	Programma wordt opgehaald uit de database
	ResultSet result = progdao.Prog(comp1, ronde1);

 
// Html page wordt aangemaakt
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
 out.println(" <h1>Het programma van competitie "+ comp +" op speeldag "+ronde+ "</h1>");
 out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"+"<tr> <th>Ronde</th> <th>Thuis</th>  <th>Uit</th> <th>DpThuis</th> <th>Dpuit</th> </tr>");
int i = 0;
while(result.next()){
	i=i+1;
out.println("<tr>"
          + "<td><center>"+result.getInt("datum")+"</center></td>"
          		+ "<td><center>"+teamdao.naamthuis(result.getInt("Thuisploeg"))+"</center></td>"
          		+ "<td><center>"+teamdao.naamuit(result.getInt("Uitploeg"))+"</center></td>"
          		+ "<td><center>"+result.getInt("Doelpunten_T")+"</center></td>"
          		+ "<td><center>"+result.getInt("Doelpunten_U")+"</center></td>"
          		+ "</tr>");
}
out.println("</table>");
 out.println("<form class='ann', action='inlog.html'>");
 out.println("    <input type='submit' value='Ga terug' />");



 out.println(" </body>");
 out.println("</html>");
} catch (Exception e) {
	e.printStackTrace();
}
 
 }}