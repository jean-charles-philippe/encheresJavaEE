

package fr.eni.ecole.encheres.bll;

import java.time.LocalDate;

public class Article {
	String nomArticle;
	String description;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	Integer miseAPrix;
	Integer prixVente;
	Integer categorie;
	Integer etatVente;
	Integer no_utilisateur;
	String imageVente;
	Integer no_article;
	String rue;
	String code_postal;
	String ville;
	String pseudo;
	
public String getImageVente() {
		return imageVente;
	}


	public void setImageVente(String imageVente) {
		this.imageVente = imageVente;
	}


	//init article
	public Article(String nomArticle, String description, LocalDate dateDebutEncheres2, LocalDate dateFinEncheres2,
			Integer miseAPrix,  Integer categorie, Integer etatVente, Integer no_utilisateur, String imageVente) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres2;
		this.dateFinEncheres = dateFinEncheres2;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.imageVente = imageVente;
	}
	

//article + retrait avec pseudo 
	public Article(Integer no_article,String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer miseAPrix, Integer prixVente, Integer categorie, Integer etatVente, Integer no_utilisateur, String pseudo,
			String rue, String code_postal, String ville, String imageVente) {
		this.no_article = no_article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.imageVente = imageVente;
	}


	
//article + retrait
	public Article(Integer no_article,String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer miseAPrix, Integer prixVente, Integer categorie, Integer etatVente, Integer no_utilisateur,
			String rue, String code_postal, String ville, String imageVente) {
		this.no_article = no_article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.imageVente = imageVente;
	}


	

//article + pseudo
	public Article(Integer no_article, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Integer miseAPrix,
			Integer prixVente, Integer categorie, Integer etatVente,Integer no_utilisateur, String pseudo, String imageVente) {
		this.no_article = no_article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.categorie = categorie;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.imageVente = imageVente;
	}
	



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public Integer getMiseAPrix() {
		return miseAPrix;
	}



	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}



	public Integer getPrixVente() {
		return prixVente;
	}



	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}



	public Integer getCategorie() {
		return categorie;
	}



	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}



	public Integer getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(Integer etatVente) {
		this.etatVente = etatVente;
	}



	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}



	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}



	public Integer getNo_article() {
		return no_article;
	}



	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCode_postal() {
		return code_postal;
	}



	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	@Override
	public String toString() {
		return "Article [nomArticle=" + nomArticle + ", description=" + description + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", categorie=" + categorie + ", etatVente=" + etatVente + "]";
	}
	
	
	
}
