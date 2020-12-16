package fr.eni.ecole.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Enchere;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.VenteManager;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur userConnected = (Utilisateur) session.getAttribute("userConnected"); 
		String achatsVentes;
		String radioAchats;	
		List<Article> listEncheresOuvertes = new ArrayList<Article>();
		VenteManager mngr = VenteManager.getInstance();
		
		if (userConnected == null) {
			updateEncheres(mngr);
			listEncheresOuvertes =  mngr.selectEncherersOuvertes();
			session.setAttribute("mesAchats", listEncheresOuvertes);
		
			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
				
		} else if (userConnected != null) {
					radioAchats = (String) session.getAttribute("radioAchats");
					achatsVentes =  (String) session.getAttribute("achatsVentes");
					updateEncheres(mngr);
					if (achatsVentes == null && radioAchats == null) {
						listEncheresOuvertes = new ArrayList<Article>();
						listEncheresOuvertes = new ArrayList<Article>();
						listEncheresOuvertes =  mngr.selectEncherersOuvertes();
						session.setAttribute("radioAchats", "1");	
						session.setAttribute("achatsVentes", "1");
						session.setAttribute("mesAchats", listEncheresOuvertes);
						
						getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
						return;
						
							} else {
								listEncheresOuvertes = new ArrayList<Article>();
								session.setAttribute("mesAchats", listEncheresOuvertes);
								
								getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

							}				
					}
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String achatsVentes;
		String radioAchats;
		String radioVentes;
		Utilisateur user;
		VenteManager mngr = VenteManager.getInstance();
//MES VENTES		
		achatsVentes = request.getParameter("radioTop_form_categorie");
		if (achatsVentes.equals("2")) {
			session.setAttribute("achatsVentes", achatsVentes);	
			radioVentes = request.getParameter("radioVentes");
			if (radioVentes == null ) {
				radioVentes = "1";
			}	
			session.setAttribute("radioVentes", radioVentes);
			List<Article> mesVentesEnCours;
			List<Article> ventesTerminees;	
			List<Article> ventesNonDebutees;
			user = (Utilisateur)(session.getAttribute("userConnected"));
			updateEncheres(mngr);
				switch (radioVentes) {
				case "1":
					mesVentesEnCours = mngr.selectArticleByUser(user.getId());
					if (mesVentesEnCours.isEmpty()) {
						request.setAttribute("pasDeVentesEnCours", "Vous n'avez aucune vente en cours.");
						session.setAttribute("mesVentes", mesVentesEnCours);
						getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
						return;
					}
					session.setAttribute("mesVentes", mesVentesEnCours);
					break;
				case "2":
					ventesNonDebutees = mngr.selectArticleByUser_vND(user.getId());
					if (ventesNonDebutees.isEmpty()) {
						request.setAttribute("ventesNonDebutees", "Vous n'avez aucune vente à venir.");
						session.setAttribute("mesVentes", ventesNonDebutees);
						getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
						return;
					}
					session.setAttribute("mesVentes", ventesNonDebutees);
					break;
				case "3":
					ventesTerminees = mngr.selectArticleByUser_vT(user.getId());
					if (ventesTerminees.isEmpty()) {
						request.setAttribute("ventesTerminees", "Vous n'avez pas encore vendu votre premier article.");
						session.setAttribute("mesVentes", ventesTerminees);
						getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
						return;
					}
					session.setAttribute("mesVentes", ventesTerminees);
					break;
				default:
					break;
				}
					

					getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
		
		
//MES ACHATS		
		if (achatsVentes.equals("1")) {
			session.setAttribute("achatsVentes", achatsVentes);
			radioAchats = request.getParameter("radioAchats");
				if (radioAchats == null ) {
					radioAchats = "1";
				}		
		
			radioVentes = null;
			session.setAttribute("radioAchats", radioAchats);
			user = (Utilisateur)(session.getAttribute("userConnected"));
			List<Article> listEncheresOuvertes = new ArrayList<Article>();
			List<Enchere> mesEncheresEnCours = new ArrayList<Enchere>();
			List<Enchere> mesEncheresRemportees = new ArrayList<Enchere>();
			updateEncheres(mngr);
			
			switch (radioAchats) {
			case "1":
				listEncheresOuvertes =  mngr.selectEncherersOuvertes();
				if (listEncheresOuvertes.isEmpty()) {
					request.setAttribute("pasDeVentesEnCours", "Aucune enchère en cours.");
					session.setAttribute("mesAchats", listEncheresOuvertes);
					getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
					return;
				}
				session.setAttribute("mesAchats", listEncheresOuvertes);
				break;
			case "2":		
				user = (Utilisateur)(session.getAttribute("userConnected"));
				mesEncheresEnCours = mngr.selectEnchereSuivie( user.getId());
			
				if (mesEncheresEnCours.isEmpty()) {
					request.setAttribute("mesEncheresEnCours", "Vous n'avez aucune enchère en cours.");
					session.setAttribute("mesAchats", listEncheresOuvertes);
					getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
					return;
				}
				session.setAttribute("mesAchats", mesEncheresEnCours);
				break;
			case "3":
				mesEncheresRemportees = mngr.selectArticleByUser_AchatsR(user.getId());
				if (mesEncheresRemportees.isEmpty()) {
					request.setAttribute("mesEncheresRemportees", "Vous n'avez pas encore remporté d'enchère.");
					session.setAttribute("mesAchats", mesEncheresRemportees);
					getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
					return;
				}
				session.setAttribute("mesAchats", mesEncheresRemportees);
				break;
			default:
				break;
			}			
			

			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

		}		

	}
	
	public void updateEncheres(VenteManager mngr) {
		List<Enchere> listFinDeVente = new ArrayList<>();
		mngr = VenteManager.getInstance();
		listFinDeVente = mngr.selectFinEnchere();
		if (listFinDeVente!=null) {
			mngr.updateVendu();
			mngr.updateNonVendu();
			for (Enchere enchere : listFinDeVente) {
				mngr.crediterAcheteur(enchere.getAcheteur(),enchere.getNo_article());
				mngr.crediterVendeur(enchere.getVendeur(),enchere.getNo_article());
				}
			}
	}
	
}

	
	



