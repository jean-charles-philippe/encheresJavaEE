package fr.eni.ecole.encheres.bll;

import java.time.LocalDate;

public class Enchere {
	LocalDate dateEnchere;
	Integer montantEnchere;
	Integer meilleure_offre;

	public Enchere(LocalDate dateEnchere, Integer montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
		
	Integer id_user_vente;
	String pseudo;
	Integer no_article;
	String nomArticle;
	String description;
	Integer miseAPrix;
	LocalDate date_enchere;
	Integer	montant_enchere;
	Integer id_user_enchere;
	String pseudo_best;
	Integer etat_vente;
	String rue;
	String code_postal;
	String ville;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	Integer prixVente;
	Integer categorie;
	Integer etatVente;
	Integer no_utilisateur;
	Integer acheteur;
	Integer vendeur;
	Integer etat_enchere;

	public Enchere(Integer id_user_vente, String pseudo, Integer no_article, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Integer miseAPrix,
			LocalDate date_enchere, Integer montant_enchere, Integer id_user_enchere, String pseudo_best, Integer etat_vente, String rue, String code_postal, String ville) {
		this.id_user_vente = id_user_vente;
		this.pseudo = pseudo;
		this.no_article = no_article;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.id_user_enchere = id_user_enchere;
		this.pseudo_best = pseudo_best;
		this.etat_vente = etat_vente;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}
	

	
//SUIVI ENCHERE	
	public Enchere(Integer no_article, Integer id_user_enchere) {
		this.no_article = no_article;
		this.id_user_enchere = id_user_enchere;
	}



	public Enchere(Integer no_article, Integer id_user_enchere, Integer meilleure_offre) {
		this.no_article = no_article;
		this.id_user_enchere = id_user_enchere;
		this.meilleure_offre = meilleure_offre;
	}

	public Enchere(Integer no_article, Integer acheteur, Integer vendeur, Integer etat_enchere) {
		this.no_article = no_article;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
		this.etat_enchere = etat_enchere;
	}
	
	
	public Integer getMeilleure_offre() {
		return meilleure_offre;
	}



	public void setMeilleure_offre(Integer meilleure_offre) {
		this.meilleure_offre = meilleure_offre;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
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



	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Integer getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
		
	public Integer getId_user_vente() {
		return id_user_vente;
	}

	public void setId_user_vente(Integer id_user_vente) {
		this.id_user_vente = id_user_vente;
	}

	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}

	public String getNom_article() {
		return nomArticle;
	}

	public void setNom_article(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public LocalDate getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(LocalDate date_enchere) {
		this.date_enchere = date_enchere;
	}

	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	public Integer getId_user_enchere() {
		return id_user_enchere;
	}

	public void setId_user_enchere(Integer id_user_enchere) {
		this.id_user_enchere = id_user_enchere;
	}

	public Integer getEtat_vente() {
		return etat_vente;
	}

	public void setEtat_vente(Integer etat_vente) {
		this.etat_vente = etat_vente;
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

	public String getPseudo_best() {
		return pseudo_best;
	}

	public void setPseudo_best(String pseudo_best) {
		this.pseudo_best = pseudo_best;
	}



	
	public Integer getAcheteur() {
		return acheteur;
	}



	public void setAcheteur(Integer acheteur) {
		this.acheteur = acheteur;
	}



	public Integer getVendeur() {
		return vendeur;
	}



	public void setVendeur(Integer vendeur) {
		this.vendeur = vendeur;
	}



	public Integer getEtat_enchere() {
		return etat_enchere;
	}



	public void setEtat_enchere(Integer etat_enchere) {
		this.etat_enchere = etat_enchere;
	}



	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}
	
}
