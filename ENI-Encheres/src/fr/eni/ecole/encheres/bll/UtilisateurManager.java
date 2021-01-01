package fr.eni.ecole.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.encheres.dal.DAOFactory;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if (instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	private UtilisateurManager() {
	}
	
	
//INSERT
	
	public void insertUtilisateur(Utilisateur user) {
		 UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		 try {
			utilisateurDAO.insertUtilisateur(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


//SELECT
	
	public Utilisateur selectByPseudo(String pseudo) {
		Utilisateur user;
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		user = utilisateurDAO.selectByPseudo(pseudo);
		return user;
	}

	public Utilisateur selectById(Integer no_utilisateur) {
		Utilisateur user;
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		user = utilisateurDAO.selectById(no_utilisateur);
		return user;
	}

	public Utilisateur login(String email, String mdp) {
		Utilisateur user;
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		user = utilisateurDAO.login(email,mdp);
		return user;
	}

	public List<Utilisateur> selectMembres() {
		List<Utilisateur> listMembres;
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		listMembres = utilisateurDAO.selectMembres();
		return listMembres;
	}
	
	
//UPDATE
	
	public void updateUtilisateur(Utilisateur user) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurDAO.updateUtilisateur(user);
		
		
	}

//DELETE
	
	public void deleteById(Integer id) {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurDAO.deleteById(id);
	}
	
}
