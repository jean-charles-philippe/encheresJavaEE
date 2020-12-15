package fr.eni.ecole.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.ecole.encheres.bll.Enchere;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.UtilisateurManager;
import fr.eni.ecole.encheres.bll.VenteManager;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VenteManager mngr = VenteManager.getInstance();
		Enchere enchere;
		Integer no_article = Integer.parseInt(request.getParameter("id"));
		enchere = mngr.selectEnchereByNo_article(no_article );
		System.out.println(enchere);
		request.setAttribute("enchere", enchere);
		getServletContext().getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur user;
		Utilisateur userVendeur = null;;
		Integer nouvelEncherisseur =	Integer.parseInt(request.getParameter("id_User_faisant_enchere"));
		Integer dernierEncherisseur = Integer.parseInt(request.getParameter("id_User_ayant_meilleur_offre_avant_enchere"));
		Integer idVendeur = Integer.parseInt(request.getParameter("id_User_vendeur"));		
		Integer no_article =Integer.parseInt(request.getParameter("enchere_no_article"));
		Integer montant_enchere = Integer.parseInt(request.getParameter("form_encherir_proposition"));
		LocalDate date_fin_enchere =LocalDate.parse( request.getParameter("form_encherir_fin"));
		
		UtilisateurManager mngrUser = UtilisateurManager.getInstance();
		user = mngrUser.selectById(nouvelEncherisseur);
		userVendeur =  mngrUser.selectById(idVendeur);
		
		if(date_fin_enchere.isBefore(LocalDate.now())) {
			request.setAttribute("EnchereTerminee", "L'enchère n'a pas aboutie, vous avez atteint le limte fixée par le vendeur.");
			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			return;
		}		
			
		if(date_fin_enchere.isAfter(LocalDate.now()) && (user.getCredit() - montant_enchere) > 0) {
			VenteManager mngr = VenteManager.getInstance();
			mngr.updateEnchere(dernierEncherisseur, no_article,nouvelEncherisseur ,montant_enchere);
			
			Enchere verif_enchere_User  = mngr.selectEnchere_en_cours(no_article,nouvelEncherisseur);
			if (verif_enchere_User == null) {
				mngr.creationSuivi(no_article,nouvelEncherisseur);
				mngr.updateSuivi(no_article,nouvelEncherisseur, montant_enchere);
			} else {
				mngr.updateSuivi(no_article,nouvelEncherisseur, montant_enchere);
			}
						
//			Integer nouveauCreditEncherisseur = user.getCredit() - montant_enchere;
//			Integer nouveauCreditVendeur = userVendeur.getCredit() + montant_enchere;
//			user.setCredit(nouveauCreditEncherisseur);
//			userVendeur.setCredit(nouveauCreditVendeur);		
//			mngrUser.updateUtilisateur(user);
//			mngrUser.updateUtilisateur(userVendeur);

		}else {
			request.setAttribute("PbCredit", "Vous ne disposez pas des crédits suffisants pour acheter. Commencez par vendre un article.");
			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			return;
		}
		

		//HttpSession session = request.getSession();	
		//session.setAttribute("no_article_encheri", no_article);
		request.setAttribute("enregistrementEnchere", "Votre enchère a bien été enregistrée.");
		getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

	}

}
