package medical.m2i.controller.ville;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medical.m2i.dao.VilleDao;
import medical.m2i.model.Ville;

/**
 * Servlet implementation class EditVilleServlet
 */
@WebServlet("/editville")
public class EditVilleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditVilleServlet() {
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

		int id = Integer.parseInt(request.getParameter("id"));

		VilleDao villeDao = new VilleDao();
		System.out.println("ok dans edit Ville");
		Ville v = villeDao.getVille(id);

		request.setAttribute("villeparam", v);

		VilleDao vdao = new VilleDao();
		List<Ville> lv;

		try {
			lv = vdao.getVillesByPays(v.getPays());
			request.setAttribute("nomville", lv);
			System.out.println(lv.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/villeEdit.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// R�cup�rer les infos soumises

		System.out.println("Je suis bien dans la méthode post");
		String pays = request.getParameter("pays");
		String nom = request.getParameter("nom");
		Integer codepostal = Integer.parseInt(request.getParameter("code_postal"));

		int id = Integer.parseInt(request.getParameter("id"));

		// Mettre � jour le ville en question
		VilleDao villeDao = new VilleDao();
		System.out.println("ok dans edit ville zz" + id);
		villeDao.editVille(id, pays, nom, codepostal);

		response.sendRedirect(request.getContextPath() + "/ListVilleServlet");

	}

}
