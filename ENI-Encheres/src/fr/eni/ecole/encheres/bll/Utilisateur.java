



package fr.eni.ecole.encheres.bll;

public class Utilisateur {
	private Integer id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private String rue;
	private String cp;
	private String ville;
	private String mdp;
	private Integer credit;
	private Integer administrateur = 0;
	

// INIT UTILISATEUR
	public Utilisateur( String pseudo, String nom, String prenom, String email, String tel, String rue, String cp,
			String ville, String mdp, Integer credit, Integer administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.administrateur = administrateur;	
	}
	
	
//UTILISATEUR AVEC SON ID ET ADMIN
	public Utilisateur( Integer id, String pseudo, String nom, String prenom, String email, String tel, String rue, String cp,
			String ville, String mdp, Integer credit, Integer administrateur) {
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	
	//UTILISATEUR AVEC SON ID POUR MODIFICATION PROFIL 
		public Utilisateur( Integer id, String pseudo, String nom, String prenom, String email, String tel, String rue, String cp,
				String ville, String mdp, Integer credit) {
			this.id = id;
			this.pseudo = pseudo;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.tel = tel;
			this.rue = rue;
			this.cp = cp;
			this.ville = ville;
			this.mdp = mdp;
			this.credit = credit;
	
		}


	public Integer getAdministrateur() {
		return administrateur;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Integer administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", credit=" + credit + ", administrateur=" + administrateur + "]";
	}
	
	
}
