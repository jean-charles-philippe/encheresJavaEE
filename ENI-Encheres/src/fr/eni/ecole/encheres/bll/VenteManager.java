
package fr.eni.ecole.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.dal.ArticleDAO;
import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.EnchereDAO;
import fr.eni.ecole.encheres.dal.RetraitDAO;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

public class VenteManager {
	private static VenteManager instance;
	
	public static  VenteManager getInstance() {
		if (instance==null) {
			instance  = new VenteManager();
		}
		return instance;
	}
	
	
	private VenteManager() {
	}


	
	
	
//INSERT	
	
	public Integer insertArticle(Article article) {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		return articleDAO.insertArticleDAO(article);
		
	}

	public void insertRetrait(Retrait retrait) {
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		retraitDAO.insertRetrait(retrait);
		
	}
	
	public void creationEnchere(Integer id_utilisateur, Integer id, LocalDate dateDebutEncheres, Integer miseAPrix) {
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		enchereDAO.creationEnchere(id_utilisateur,id,dateDebutEncheres,miseAPrix);
		
	}

	public void creationSuivi(Integer id, Integer id_utilisateur) {
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		enchereDAO.creationSuivi(id, id_utilisateur);
		
	}	
	
//SELECT

	public Article selectArticleByNo_article(Integer no_article) {
		Article article;
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		article = articleDAO.selectArticleByNo_article(no_article);
		return article;
	}

	public List<Article> selectEncherersOuvertes() {
		List<Article> listEncheresOuvertes;
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		listEncheresOuvertes = articleDAO.selectEncherersOuvertes();
		return listEncheresOuvertes;
	}

	public Enchere selectEnchereByNo_article(Integer no_article) {
		Enchere enchere;
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		enchere = enchereDAO.selectEnchereByNo_article(no_article);
		return enchere;
	}

	public List<Article> selectArticleByUser(Integer id) {
		List<Article> mesVentesEnCours;
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		mesVentesEnCours = articleDAO.selectArticleByUser(id);
		return mesVentesEnCours;
	}

	public List<Article> selectArticleByUser_vND(Integer id) {
		List<Article> ventesNonDebutees;
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		ventesNonDebutees = articleDAO.selectArticleByUser_vND(id);
		return ventesNonDebutees;
	}

	public List<Article> selectArticleByUser_vT(Integer id) {
		List<Article> ventesTerminees;
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		ventesTerminees = articleDAO.selectArticleByUser_vT(id);
		return ventesTerminees;
	}

	public Enchere selectEnchere_en_cours(Integer no_article, Integer nouvelEncherisseur) {
		Enchere enchere;
		EnchereDAO enchereDao = DAOFactory.getEnchereDAO();
		enchere = enchereDao.selectEnchere_en_cours( no_article,  nouvelEncherisseur);
		return enchere;
	}
	
	public List<Enchere> selectEnchereSuivie(Integer nouvelEncherisseur) {
		List<Enchere> suiviEnchere_User;
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		suiviEnchere_User = enchereDAO.selectEnchereSuivie(nouvelEncherisseur);
		return suiviEnchere_User;
	}
	
	public List<Enchere> selectFinEnchere() {
		List<Enchere> listFinEnchere;
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		listFinEnchere = enchereDAO.selectFinEnchere();
		return listFinEnchere;
	}
	
	public List<Enchere> selectArticleByUser_AchatsR(Integer id) {
		List<Enchere> achatsRemportes;
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		achatsRemportes = enchereDAO.selectArticleByUser_AchatsR(id);
		return achatsRemportes;
	}
	



//UPDATE
	
	public void updateArticleEnVente(Article article_a_MAJ) {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		articleDAO.updateArticleEnVente(article_a_MAJ);
	}
	
	public void updateEnchere(Integer dernierEncherisseur, Integer no_article, Integer nouvelEncherisseur, Integer montant_enchere) {
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		enchereDAO.updateEnchere(dernierEncherisseur,  no_article,nouvelEncherisseur, montant_enchere);
		
	}
	
	public void updateVendu() {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		articleDAO.updateVendu();
		
	}

	public void updateNonVendu() {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		articleDAO.updateNonVendu();
		
	}

	public void deleteArticle_Enchere(Integer no_article) {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		articleDAO.deleteArticle_Enchere(no_article);
		
	}


	public void updateSuivi(Integer no_article, Integer nouvelEncherisseur, Integer montant_enchere) {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		articleDAO.updateSuivi( no_article, nouvelEncherisseur, montant_enchere);
	}

	public void crediterAcheteur(Integer acheteur, Integer no_article) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurDAO.crediterAcheteur(acheteur, no_article);
		
	}


	public void crediterVendeur(Integer vendeur, Integer no_article) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurDAO.crediterVendeur(vendeur, no_article) ;
		
	}











}
