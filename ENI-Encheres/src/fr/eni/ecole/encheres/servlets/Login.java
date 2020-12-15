package fr.eni.ecole.encheres.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String email = null;
		String mdp = null;
		Utilisateur user = null;
		email = request.getParameter("email_form_login");
		mdp = request.getParameter("password_form_login");
		UtilisateurManager mngr = UtilisateurManager.getInstance();
		user = mngr.login(email,mdp);
		
		if (user!=null) {
			session.setAttribute("userConnected", user);
			response.sendRedirect("index");
			
		}else {
			request.setAttribute("erreurLogin", "Votre identifiant ou votre mot de passe n'est pas correct.");
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
	}

}
