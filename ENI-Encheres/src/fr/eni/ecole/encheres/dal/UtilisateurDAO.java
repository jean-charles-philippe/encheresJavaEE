package fr.eni.ecole.encheres.dal;

import java.sql.SQLException;

import fr.eni.ecole.encheres.bll.Utilisateur;


public interface UtilisateurDAO {

//INSERT
	public void insertUtilisateur(Utilisateur user) throws SQLException;

//SELECT
	public Utilisateur login(String email, String mdp);

	public Utilisateur selectByPseudo(String pseudo);
	
	Utilisateur selectById(Integer no_utilisateur);
	
//UPDATE
	public void updateUtilisateur(Utilisateur user);
	
	public void crediterAcheteur(Integer acheteur, Integer no_article);
	
	void crediterVendeur(Integer vendeur, Integer no_article);
	
//DELETE
	public void deleteById(Integer id);






		
	
	

}
