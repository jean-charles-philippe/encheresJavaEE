package fr.eni.ecole.encheres.dal;

import fr.eni.ecole.jdbcTools.ArticleDAOJdbcImpl;
import fr.eni.ecole.jdbcTools.EnchereDAOJdbcImpl;
import fr.eni.ecole.jdbcTools.RetraitDAOJdbcImpl;
import fr.eni.ecole.jdbcTools.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		return utilisateurDAO;
		
	}

	public static ArticleDAO getArticleDAO() {
		ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
		return articleDAO;
	}

	public static RetraitDAO getRetraitDAO() {
		RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
		return retraitDAO;
	}
	
	public static EnchereDAO getEnchereDAO() {
		EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
		return enchereDAO;
	}	
}
