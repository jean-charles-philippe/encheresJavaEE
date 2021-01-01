package fr.eni.ecole.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.ecole.encheres.bll.Enchere;

public interface EnchereDAO {

//INSERT
	void creationEnchere(Integer id_utilisateur, Integer id, LocalDate dateDebutEncheres, Integer miseAPrix);
//SELECT
	public Enchere selectEnchereByNo_article(Integer no_article);
	public List<Enchere> selectEnchereSuivie(Integer nouvelEncherisseur);
	public Enchere selectEnchere_en_cours(Integer no_article, Integer nouvelEncherisseur);
	List<Enchere> selectFinEnchere();
	List<Enchere> selectArticleByUser_AchatsR(Integer id);
//UPDATE
	void updateEnchere(Integer dernierEncherisseur, Integer no_article, Integer nouvelEncherisseur, Integer montant_enchere);
	void creationSuivi(Integer id, Integer id_utilisateur);




		
}
