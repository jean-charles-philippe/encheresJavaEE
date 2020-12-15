package fr.eni.ecole.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo;
		pseudo = request.getParameter("pseudo");
		if (pseudo!=null) {
			UtilisateurManager mngr = UtilisateurManager.getInstance();
			Utilisateur userConsult = mngr.selectByPseudo(pseudo);
			request.setAttribute("profil", userConsult);
			getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Erreur de récupération de profil");
		}
		
	}
}
