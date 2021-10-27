package com.medicale.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicale.bo.Patient;

/**
 * Servlet implementation class Test
 */
@WebServlet("/bonjour")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println(" je suis dans le doPost");

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String dateNaissance = request.getParameter("dateNaissance");
		String adresse = request.getParameter("adresse");
		String pays = request.getParameter("pays");
		String ville = request.getParameter("ville");
		if (nom == null)
			nom = "";
		if (prenom == null)
			prenom = "";
		if (dateNaissance == null)
			dateNaissance = " abscence de date de naissance ";
		Patient pat = new Patient(nom, prenom, Date.valueOf(dateNaissance), adresse, pays, ville);
		Patient.addPatient(pat);

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("    <head>");
			out.println("        <title>Page prenom</title>");
			out.println("    </head>");
			out.println("    <body>");
			out.println("        <h1>Bonjour " + nom + " " + prenom + "</h1>");
			out.println("<p>   vous etes né le  :  " + dateNaissance + "</p>");
			out.println(" <p> adresse complet : " + adresse + " " + ville + " " + pays + "</p>");
			out.println("    </body>");
			out.println("</html>");

		}
	}

}
