package nl.hu.v1ipass.test.servlets;
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
import nl.hu.v1ipass.test.servlets.Codes;

@WebServlet(urlPatterns = "/Programma.java")

public class Programma extends HttpServlet {
 protected void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException {
		Codes dao = new Codes();
		ProgrammaDAOImpl progdao = new ProgrammaDAOImpl();
		
 String comp = req.getParameter("comp");
 String ronde = req.getParameter("ronde");

 int comp1 = Integer.parseInt(comp);
 int ronde1 = Integer.parseInt(ronde);


try {
	ArrayList<Integer> wedstrijden = progdao.findProgrammaTeam(comp1);
	ArrayList<ProgrammaPOJO> prog = progdao.findProgramma(wedstrijden);

 

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
 out.println(" <h1>Het programma van competitie "+ comp + "</h1>");
 out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=100%>"+"<tr> <th>Ronde</th> <th>Thuis</th>  <th>Uit</th> <th>DpThuis</th> <th>Dpuit</th> </tr>");
int i = 0;
for(ProgrammaPOJO n : prog){
	i=i+1;
out.println("<tr>"
          + "<td><center>"+n.getRonde()+"</center></td>"
          		+ "<td><center>"+dao.naamthuis(n.getThuis())+"</center></td>"
          		+ "<td><center>"+dao.naamuit(n.getUit())+"</center></td>"
          		+ "<td><center>"+n.getDoelpuntenthuis()+"</center></td>"
          		+ "<td><center>"+n.getDoelpuntenuit()+"</center></td>"
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