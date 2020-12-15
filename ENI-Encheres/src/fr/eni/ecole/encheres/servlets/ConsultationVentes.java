package fr.eni.ecole.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.VenteManager;

/**
 * Servlet implementation class ConsultationVentes
 */
@WebServlet("/consultationVentes")
public class ConsultationVentes extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Article article_a_MAJ;
		request.setCharacterEncoding("UTF-8");
		Integer no_article =Integer.parseInt(request.getParameter("id"));
		VenteManager mngr = VenteManager.getInstance();
		article_a_MAJ = mngr.selectArticleByNo_article(no_article);		
			
		session.setAttribute("article_a_MAJ", article_a_MAJ);
		
		getServletContext().getRequestDispatcher("/WEB-INF/mesVenteEnCours.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer no_article = Integer.parseInt((String) request.getParameter("id_article_aModifier"));
		VenteManager mngr = VenteManager.getInstance();
		HttpSession session = request.getSession();
		if (request.getParameter("annulerVente") != null) {
			Utilisateur user = (Utilisateur)(session.getAttribute("userConnected"));	
			List<Article> ventesNonDebutees;
			mngr.deleteArticle_Enchere(no_article);
			request.setAttribute("suppressionEnchere", "Votre enchère est bien supprimée.");
			ventesNonDebutees = mngr.selectArticleByUser_vND(user.getId());
			session.setAttribute("mesVentes", ventesNonDebutees);
			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			return;
		}
		
		Article article_a_MAJ;
		request.setCharacterEncoding("UTF-8");		
		
		String nomArticle = request.getParameter("form_consultationVentes_article");
		String description = request.getParameter("form_consultationVentes_description");
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("form_consultationVentes_debut"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("form_consultationVentes_fin"));
		Integer miseAPrix = Integer.parseInt(request.getParameter("form_consultationVentes_prix"));
		Integer prixVente = null;
		Integer categorie = Integer.parseInt(request.getParameter("form_consultationVentes_categorie"));
		Integer etatVente = 0;
		Integer id_utilisateur = Integer.parseInt(request.getParameter("id_Utilisateur_recuperation_article"));
		String rue = request.getParameter("rue_form_consultationVentes_rue");
		String code_postal = request.getParameter("cp_form_consultationVentes_cp");
		String ville = request.getParameter("ville_form_consultationVentes_ville");

		article_a_MAJ = new Article(no_article, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, categorie, etatVente, id_utilisateur, rue, code_postal, ville);
		mngr.updateArticleEnVente(article_a_MAJ);
		
		article_a_MAJ = mngr.selectArticleByNo_article(no_article);		
		session.setAttribute("article_a_MAJ", article_a_MAJ);
		request.setAttribute("articleUpdated", "Votre modification a bien été prise en compte.");
		getServletContext().getRequestDispatcher("/WEB-INF/mesVenteEnCours.jsp").forward(request, response);
	}

}
