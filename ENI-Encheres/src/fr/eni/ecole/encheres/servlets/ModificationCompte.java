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
 * Servlet implementation class ModificationCompte
 */
@WebServlet("/modificationCompte")
public class ModificationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Utilisateur userUpdate;
		Utilisateur userUpdated;
		Integer id = Integer.parseInt(request.getParameter("idUpdate"));
		String pseudo = request.getParameter("pseudo_form_modification_pseudo");
		String nom = request.getParameter("nom_form_modification_nom");
		String prenom = request.getParameter("prenom_form_modification_prenom");
		String email = request.getParameter("email_form_modification_email");
		String tel = request.getParameter("telephone_form_modification_tel");
		String rue = request.getParameter("rue_form_modification_rue");
		String cp = request.getParameter("cp_form_modification_cp");
		String ville = request.getParameter("ville_form_modification_ville");
	
		String mdpAverifier = request.getParameter("password_form_modification_new");
		String mdp = request.getParameter("password_form_modification_new_confirmation");
		if (!mdpAverifier.equals(mdp)) {
			request.setAttribute("checkPassword", "Les mots de passe saisis ne sont pas identiques!");
			getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
			return;
		}
		
		Integer credit = Integer.parseInt(request.getParameter("credit"));
		if (mdp.isEmpty()) {
			mdp = request.getParameter("password_form_modification_current");
		}
		userUpdate = new Utilisateur(id, pseudo, nom, prenom, email, tel, rue, cp, ville, mdp, credit);
		UtilisateurManager mngr = UtilisateurManager.getInstance();
		
		mngr.updateUtilisateur(userUpdate);
		userUpdated = mngr.selectByPseudo(pseudo);
		session.removeAttribute("userConnected");
		session.setAttribute("userConnected", userUpdated);
		request.setAttribute("updateProfile", "Votre profil a bien été mis à jour.");
		
		getServletContext().getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
		
	}

}
