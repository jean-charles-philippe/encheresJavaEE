package fr.eni.ecole.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fr.eni.ecole.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("Iddelete_form_modification"));
		UtilisateurManager mngr = UtilisateurManager.getInstance();
		mngr.deleteById(id);
		
		response.sendRedirect("logout");

	}

}
