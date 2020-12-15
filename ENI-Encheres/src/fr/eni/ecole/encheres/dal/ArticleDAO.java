package fr.eni.ecole.encheres.dal;


import java.util.List;
import fr.eni.ecole.encheres.bll.Article;


public interface ArticleDAO {

//INSERT	
	Integer insertArticleDAO(Article article);


//SELECT
	Article selectArticleByNo_article(Integer no_article);

	List<Article> selectEncherersOuvertes();


	List<Article> selectArticleByUser(Integer id);


	List<Article> selectArticleByUser_vND(Integer id);


	List<Article> selectArticleByUser_vT(Integer id);
	

//UPDATE
	void updateVendu();
	
	void updateArticleEnVente(Article article_a_MAJ);

	void deleteArticle_Enchere(Integer no_article);

	void updateSuivi(Integer no_article, Integer nouvelEncherisseur, Integer meilleure_offre);


	void updateNonVendu();



	
}
