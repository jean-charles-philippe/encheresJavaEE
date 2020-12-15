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
 * Servlet implementation class CreationCompte
 */
@WebServlet("/creationCompte")
public class CreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/creationCompte.jsp").forward(request, response);
	}

	/**
	 * @param Utilisateur 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo;
		String nom;
		String prenom;
		String email;
		String tel;
		String rue;
		String cp;
		String ville;
		String mdp;
		Integer credit =0;
		Integer administrateur = 0;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");		
		pseudo=request.getParameter("pseudo_form_creation");
		nom=request.getParameter("nom_form_creation");
		prenom=request.getParameter("prenom_form_creation");
		email=request.getParameter("email_form_creation");
		tel=request.getParameter("telephone_form_creation");
		rue=request.getParameter("rue_form_creation");
		cp=request.getParameter("cp_form_creation");
		ville=request.getParameter("ville_form_creation");
		mdp=request.getParameter("password_form_creation_confirm");

		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, credit, administrateur);
		UtilisateurManager mngr = UtilisateurManager.getInstance();
		mngr.insertUtilisateur(user);
		
		session.setAttribute("userConnected", user);
		response.sendRedirect("index");

	}

}
