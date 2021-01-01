package fr.eni.ecole.jdbcTools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.dal.ArticleDAO;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String INSERT_ARTICLE_SQL = "INSERT INTO articles"
			+ " (nom_article, description, date_debut_encheres, date_fin_encheres,prix_initial,no_utilisateur,no_categorie, etat_vente, imageVente)"
			+ "  VALUES (?,?,?,?,?,?,?,?,?) ";
	
	private static final String SELECT_ID_SQL = "SELECT @@IDENTITY";
	
	private static final String SELECT_ARTICLE_SQL = "SELECT * FROM articles"
			+ " JOIN RETRAITS ON articles.no_article= retraits.no_article"
			+ " WHERE no_utilisateur=? AND etat_vente=0  AND GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres ";
	
	private static final String SELECT_ARTICLE_BY_ID_SQL = "SELECT * FROM articles"
			+ " JOIN RETRAITS ON articles.no_article= retraits.no_article "
			+ "JOIN UTILISATEURS ON utilisateurs.no_utilisateur = articles.no_utilisateur "
			+ "JOIN ENCHERE ON articles.no_article = enchere.no_article "
			+ "WHERE articles.no_article=?";
	
	private static final String SELECT_ARTICLE_ENVENTE_SQL = "SELECT * FROM articles\r\n" + 
			"JOIN ENCHERE ON articles.no_article = enchere.no_article\r\n" + 
			"JOIN UTILISATEURS ON utilisateurs.no_utilisateur = articles.no_utilisateur\r\n" + 
			"WHERE  etat_vente=0 AND  GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres";
	
	private static final String SELECT_ARTICLE_VENTE_TERMINEE =	"SELECT * FROM articles "
			+ "JOIN ENCHERE ON articles.no_article= ENCHERE.no_article "
			+ "JOIN RETRAITS ON articles.no_article= retraits.no_article "
			+ "WHERE articles.no_utilisateur=? AND GETDATE()>date_fin_encheres";
	
	private static final String SELECT_ARTICLE_VENTE_NON_DEBUTEE =	"SELECT * FROM articles "
			+ "JOIN ENCHERE ON articles.no_article= ENCHERE.no_article "
			+ "JOIN RETRAITS ON articles.no_article= retraits.no_article "
			+ "WHERE articles.no_utilisateur=? AND GETDATE()<date_debut_encheres";
	

	
	
	private static final String UPDATE_VENDU_SQL = "UPDATE articles \r\n" + 
			"			SET etat_vente=1, prix_vente=ENCHERE.montant_enchere, etat_enchere=1 \r\n" + 
			"			FROM articles\r\n" + 
			"			JOIN ENCHERE ON articles.no_article = enchere.no_article\r\n" + 
			"			JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur=ARTICLES.no_utilisateur\r\n" + 
			"			WHERE articles.date_fin_encheres<GETDATE()   AND ENCHERE.no_utilisateur != ARTICLES.no_utilisateur";
	
	
	private static final String UPDATE_NON_VENDU_SQL = "UPDATE articles \r\n" + 
			"			SET etat_enchere=1\r\n" + 
			"			FROM articles\r\n" + 
			"			JOIN ENCHERE ON articles.no_article = enchere.no_article\r\n" + 
			"			JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur=ARTICLES.no_utilisateur\r\n" + 
			"			WHERE articles.date_fin_encheres<GETDATE()   AND ENCHERE.no_utilisateur = ARTICLES.no_utilisateur";
	

	private static final String UPDATE_ARTICLE_EN_VENTE_SQL = "UPDATE articles \r\n" + 
			"			SET nom_article=?, description=?, no_categorie=?, prix_initial=?, date_debut_encheres=?, date_fin_encheres=?\r\n" + 
			"			WHERE no_article=?\r\n" + 
			"			UPDATE retraits\r\n" + 
			"			SET rue=?, code_postal=?, ville=?\r\n" + 
			"			WHERE no_article=?\r\n" + 
			"			UPDATE enchere\r\n" + 
			"			SET date_enchere=?, montant_enchere=?\r\n" + 
			"			WHERE no_article=?;	";	
	
	private static final String UPDATE_SUIVI_SQL = "UPDATE suivi "
			+ "SET meilleure_offre=? "
			+ "WHERE no_article=? AND no_utilisateur=?";
	
	private static final String DELETE_ARTICLE_ENCHERE_SQL = "DELETE FROM ARTICLES WHERE no_article=?";			
	
			
	
//INSERT	
	@Override
	public Integer insertArticleDAO(Article article) {
		Connection cnx =null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		Integer id_article_insere =null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(INSERT_ARTICLE_SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getNo_utilisateur());
			pstmt.setInt(7, article.getCategorie());
			pstmt.setInt(8, article.getEtatVente());
			pstmt.setString(9, article.getImageVente());

			pstmt.executeUpdate();
			
			pstmt1 = cnx.prepareStatement(SELECT_ID_SQL);
			rs = pstmt1.executeQuery();
			
			while (rs.next()) {
				id_article_insere = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1!=null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		JdbcTools.closeConnection();
		return id_article_insere;
	}
	
//SELECT	
	@Override
	public List<Article> selectArticleByUser(Integer id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> mesVentesEnCours = new ArrayList<>();
		Article vente;
		
		try {
			cnx = JdbcTools.getConnection();
			
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_SQL);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vente = new Article( rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"), rs.getInt("etat_vente"), rs.getInt("no_utilisateur"),  rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),rs.getString("imageVente") );
				mesVentesEnCours.add(vente);
			}			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcTools.closeConnection();
		}
			
		return mesVentesEnCours;
	}

	@Override
	public List<Article> selectEncherersOuvertes() {
		List<Article> listEncheresOuvertes = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article= null;
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_ENVENTE_SQL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article( rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"), rs.getInt("etat_vente"), rs.getInt("no_utilisateur"), rs.getString("pseudo"),rs.getString("imageVente"));
				listEncheresOuvertes.add(article);
			}			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcTools.closeConnection();
		}
				
		
		return listEncheresOuvertes;
	}	
	
	@Override
	public Article selectArticleByNo_article(Integer no_article) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article= null;
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID_SQL);
			pstmt.setInt(1, no_article);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article( rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"), rs.getInt("etat_vente"), rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),rs.getString("imageVente"));
			}			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcTools.closeConnection();
		}
			
		return article;
	}

	@Override
	public List<Article> selectArticleByUser_vND(Integer id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> ventesNonDebutees = new ArrayList<>();
		Article vente;
		
		try {
			cnx = JdbcTools.getConnection();
			
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENTE_NON_DEBUTEE);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vente = new Article( rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"), rs.getInt("etat_vente"), rs.getInt("no_utilisateur"),  rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),rs.getString("imageVente"));
				ventesNonDebutees.add(vente);
			}			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcTools.closeConnection();
		}
			
		return ventesNonDebutees;
	}

	@Override
	public List<Article> selectArticleByUser_vT(Integer id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Article> ventesTerminees = new ArrayList<>();
		Article vente;
		
		try {
			cnx = JdbcTools.getConnection();
			
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_VENTE_TERMINEE);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vente = new Article( rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_categorie"), rs.getInt("etat_vente"), rs.getInt("no_utilisateur"),  rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"),rs.getString("imageVente"));
				ventesTerminees.add(vente);
			}			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcTools.closeConnection();
		}
			
		return ventesTerminees;
	}
	
	

//UPDATE
	@Override
	public  void updateVendu() {

		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_VENDU_SQL);

			pstmt.executeUpdate();
			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				JdbcTools.closeConnection();
			}
	

	}
	
	@Override
	public  void updateNonVendu() {

		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_NON_VENDU_SQL);

			pstmt.executeUpdate();
			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				JdbcTools.closeConnection();
			}
	

	}
	
	
	@Override
	public void updateSuivi(Integer no_article, Integer nouvelEncherisseur, Integer meilleure_offre) {

		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_SUIVI_SQL);
			pstmt.setInt(1, meilleure_offre);
			pstmt.setInt(2, no_article);
			pstmt.setInt(3, nouvelEncherisseur);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				JdbcTools.closeConnection();
			}
	
			
		
	}
	
	@Override
	public void updateArticleEnVente(Article article_a_MAJ) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_ARTICLE_EN_VENTE_SQL);
			pstmt.setString(1, article_a_MAJ.getNomArticle());
			pstmt.setString(2, article_a_MAJ.getDescription());			
			pstmt.setInt(3, article_a_MAJ.getCategorie());	
			pstmt.setInt(4, article_a_MAJ.getMiseAPrix());	
			pstmt.setDate(5, Date.valueOf(article_a_MAJ.getDateDebutEncheres()));	
			pstmt.setDate(6, Date.valueOf(article_a_MAJ.getDateFinEncheres()));
			pstmt.setInt(7, article_a_MAJ.getNo_article());
			pstmt.setString(8, article_a_MAJ.getRue());			
			pstmt.setString(9, article_a_MAJ.getCode_postal());	
			pstmt.setString(10, article_a_MAJ.getVille());	
			pstmt.setInt(11, article_a_MAJ.getNo_article());		
			pstmt.setDate(12, Date.valueOf(article_a_MAJ.getDateDebutEncheres()));		
			pstmt.setInt(13, article_a_MAJ.getMiseAPrix());			
			pstmt.setInt(14, article_a_MAJ.getNo_article());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				JdbcTools.closeConnection();
			}
	}

	@Override
	public void deleteArticle_Enchere(Integer no_article) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(DELETE_ARTICLE_ENCHERE_SQL);
			pstmt.setInt(1, no_article);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				JdbcTools.closeConnection();
			}
		
	}



}
