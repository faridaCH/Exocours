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

//		response.setContentType("text/html");
//		try (PrintWriter out = response.getWriter()) {
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("    <head>");
//			out.println("        <title>Page prenom</title>");
//			out.println("    </head>");
//			out.println("    <body>");
//			out.println("        <h1>Bonjour " + nom + " " + prenom + "</h1>");
//			out.println("<p>   vous etes né le  :  " + dateNaissance + "</p>");
//			out.println(" <p> adresse complet : " + adresse + " " + ville + " " + pays + "</p>");
//			out.println("    </body>");
//			out.println("</html>");
//
//		}

		/*
		 * ****************************************************************************
		 */
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {

			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
			out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			out.println("<title>Liste des patients</title>");
			out.println(
					"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'>");
			out.println("<link rel='stylesheet' href='assets/style-connecte.css'>");
			out.println(
					"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>");
			out.println("</head>");
			out.println("</head>");
			out.println("<body>");

			out.println("<table class='table table-striped table-hover'>");
			out.println("<thead>");
			out.println("<tr>");
			out.println("<th class='text-danger'>Id</th>");
			out.println("<th class='text-success'>Nom</th>");
			out.println("<th class='text-primary'>Prénom</th>");
			out.println("<th class='text-warning'>Date de Naissance</th>");
			out.println("<th class='text-info'>Adresse</th>");
			out.println("<th>Pays</th>");
			out.println("<th>Ville</th>");
			out.println("<th><i class='fa fa-cogs' aria-hidden='true'></i></th>");
			out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			out.println("<tr>");
			out.println("<td>" + "$i" + "</td>");
			out.println("<td>" + nom + "</td>");
			out.println("<td>" + prenom + "</td>");
			out.println("<td>" + dateNaissance + "</td>");
			out.println("<td>" + adresse + "</td>");
			out.println("<td>" + pays + "</td>");
			out.println("<td>" + ville + "</td>");
			out.println("<td>");
			out.println("<button type='button' class='btn btn-outline-success btn-sm'>");
			out.println(" <i class='fa fa-pencil-square-o' aria-hidden='true'></i>");
			out.println("</button>");
			out.println("<button type='button' class='btn btn-outline-danger btn-sm'>");
			out.println("<i class='fa fa-trash' aria-hidden='true'></i>");
			out.println("</button>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
