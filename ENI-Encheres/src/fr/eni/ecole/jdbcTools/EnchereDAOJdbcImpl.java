package fr.eni.ecole.jdbcTools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Enchere;
import fr.eni.ecole.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT_ENCHERE_SQL = "INSERT INTO enchere"
			+ "  VALUES (?, ?, ?, ?) ";

	private static final String INSERT_SUIVI_SQL = "INSERT INTO suivi"
			+ "(no_article, no_utilisateur)"
			+ "  VALUES (?, ?) ";	
	
	
	private static final String SELECT_ENCHERE_BY_ID_SQL = "SELECT utilisateurs.no_utilisateur as id_user_vente , utilisateurs.pseudo, "
			+ "ARTICLES.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, date_enchere, montant_enchere, "
			+ "ENCHERE.no_utilisateur as id_user_enchere, "
			+ "(SELECT pseudo FROM UTILISATEURS WHERE no_utilisateur=Enchere.no_utilisateur) pseudo_best, "
			+ "etat_vente, retraits.rue, retraits.code_postal, retraits.ville "
			+ "FROM articles\r\n" + 
			"JOIN UTILISATEURS ON utilisateurs.no_utilisateur = articles.no_utilisateur\r\n" + 
			"JOIN ENCHERE ON articles.no_article = enchere.no_article \r\n" + 
			"JOIN RETRAITS ON articles.no_article= retraits.no_article\r\n" + 
			"WHERE articles.no_article=?";
	
	
	private static final String SELECT_ENCHERE_SUIVI_SQL = "SELECT  articles.no_utilisateur as id_user_vente , (SELECT pseudo FROM UTILISATEURS WHERE no_utilisateur=ARTICLES.no_utilisateur) pseudo , \r\n" + 
			"			ARTICLES.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, date_enchere, montant_enchere, \r\n" + 
			"			ENCHERE.no_utilisateur as id_user_enchere, \r\n" + 
			"			(SELECT pseudo FROM UTILISATEURS WHERE no_utilisateur=Enchere.no_utilisateur)  pseudo_best, \r\n" + 
			"			etat_vente, retraits.rue, retraits.code_postal, retraits.ville \r\n" + 
			"			FROM suivi \r\n" + 
			"			JOIN UTILISATEURS ON utilisateurs.no_utilisateur = suivi.no_utilisateur\r\n" + 
			"			JOIN ENCHERE ON suivi.no_article = enchere.no_article  AND suivi.no_article = ENCHERE.no_article  \r\n" + 
			"			JOIN RETRAITS ON suivi.no_article= retraits.no_article\r\n" + 
			"			JOIN articles ON articles.no_article = SUIVI.no_article\r\n" + 
			"			WHERE SUIVI.no_utilisateur=? AND GETDATE() BETWEEN articles.date_debut_encheres AND articles.date_fin_encheres ";
	
	private static final String SELECT_ENCHERE_SQL = "	SELECT * \r\n" + 
			"			FROM SUIVI\r\n" + 
			"			WHERE no_article=? AND no_utilisateur=?";
	
	private static final String SELECT_FIN_ENCHERE_SQL = "			SELECT ENCHERE.no_article,  ENCHERE.no_utilisateur as acheteur, articles.no_utilisateur as vendeur, etat_enchere\r\n" + 
			"			FROM ENCHERE\r\n" + 
			"			JOIN ARTICLES ON ARTICLES.no_article=ENCHERE.no_article\r\n" + 
			"			WHERE  etat_enchere=0 AND articles.date_fin_encheres<GETDATE()";
	
	private static final String SELECT_ARTICLE_MES_ENCHERES_REMPORTEES =	"SELECT utilisateurs.no_utilisateur as id_user_vente , utilisateurs.pseudo, \r\n" + 
			"			ARTICLES.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, date_enchere, montant_enchere, \r\n" + 
			"			ENCHERE.no_utilisateur as id_user_enchere, \r\n" + 
			"			(SELECT pseudo FROM UTILISATEURS WHERE no_utilisateur=Enchere.no_utilisateur) pseudo_best, \r\n" + 
			"			etat_vente, retraits.rue, retraits.code_postal, retraits.ville \r\n" + 
			"			FROM articles\r\n" + 
			"			JOIN UTILISATEURS ON utilisateurs.no_utilisateur = articles.no_utilisateur\r\n" + 
			"			JOIN ENCHERE ON articles.no_article = enchere.no_article \r\n" + 
			"			JOIN RETRAITS ON articles.no_article= retraits.no_article \r\n" + 
			"			WHERE  enchere.no_utilisateur=? AND etat_vente=1 AND etat_enchere=1";
	
	private static final String UPDATE_ENCHERE_SQL = "UPDATE enchere\r\n" + 
			"	SET no_utilisateur=? ,date_enchere=GETDATE() ,montant_enchere=?\r\n" + 
			"	FROM ENCHERE\r\n" + 
			"	JOIN articles ON articles.no_article = enchere.no_article\r\n" + 
			"	WHERE ENCHERE.no_utilisateur=? AND ENCHERE.no_article=? AND articles.date_fin_encheres>GETDATE() ";
	

	
//INSERT	
	@Override
	public void creationEnchere(Integer id_utilisateur, Integer id, LocalDate dateDebutEncheres, Integer miseAPrix ) {

		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(INSERT_ENCHERE_SQL); 
				pstmt.setInt(1, id_utilisateur );
				pstmt.setInt(2, id);
				pstmt.setDate(3, Date.valueOf(dateDebutEncheres));
				pstmt.setInt(4, miseAPrix);

				
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
	public void creationSuivi(Integer id, Integer id_utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(INSERT_SUIVI_SQL); 

				pstmt.setInt(1, id );
				pstmt.setInt(2, id_utilisateur);

				
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
	
//SELECT	
	@Override
	public Enchere selectEnchereByNo_article(Integer no_article) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere enchere= null;
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_ID_SQL);
			pstmt.setInt(1, no_article);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("id_user_vente"), rs.getString("pseudo"), rs.getInt("no_article"), rs.getString("nom_article"),rs.getString("description") , rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),rs.getInt("prix_initial") ,rs.getDate("date_enchere").toLocalDate() ,rs.getInt("montant_enchere") ,rs.getInt("id_user_enchere"),rs.getString("pseudo_best") ,rs.getInt("etat_vente") ,rs.getString("rue"), rs.getString("code_postal"),rs.getString("ville") );
			}			
			
			System.out.println("dans le jdbc" + enchere);
			
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
			
		return enchere;
	}
	
	@Override
	public List<Enchere>  selectEnchereSuivie( Integer nouvelEncherisseur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Enchere>  suiviEnchere_User= new ArrayList<Enchere>();
		Enchere enchere = null;
		

		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ENCHERE_SUIVI_SQL);
			pstmt.setInt(1, nouvelEncherisseur);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				enchere = new Enchere(rs.getInt("id_user_vente"), rs.getString("pseudo"), rs.getInt("no_article"), rs.getString("nom_article"),rs.getString("description") , rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),rs.getInt("prix_initial") ,rs.getDate("date_enchere").toLocalDate() ,rs.getInt("montant_enchere") ,rs.getInt("id_user_enchere"),rs.getString("pseudo_best") ,rs.getInt("etat_vente") ,rs.getString("rue"), rs.getString("code_postal"),rs.getString("ville") );
				suiviEnchere_User.add(enchere);
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
			
		return suiviEnchere_User;
	}
	

	@Override
	public Enchere selectEnchere_en_cours(Integer no_article, Integer nouvelEncherisseur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere  enchere = null;

		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ENCHERE_SQL);
			pstmt.setInt(1, no_article);		
			pstmt.setInt(2, nouvelEncherisseur);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				enchere = new Enchere( rs.getInt("no_article"), rs.getInt("no_utilisateur"), rs.getInt("meilleure_offre"));
			
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
			
		return enchere;
	}
	
	@Override
	public List<Enchere> selectFinEnchere() {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Enchere>  listFinEnchere= new ArrayList<Enchere>();
		Enchere enchere = null;
		

		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_FIN_ENCHERE_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				enchere = new Enchere(rs.getInt("no_article"), rs.getInt("acheteur"), rs.getInt("vendeur"), rs.getInt("etat_enchere"));
				listFinEnchere.add(enchere);
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
			
		return listFinEnchere;
	}
	
	@Override
	public List<Enchere> selectArticleByUser_AchatsR(Integer id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Enchere> achatsRemportes = new ArrayList<>();
		Enchere vente;
		
		try {
			cnx = JdbcTools.getConnection();
			
			pstmt = cnx.prepareStatement(SELECT_ARTICLE_MES_ENCHERES_REMPORTEES);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vente = new Enchere(rs.getInt("id_user_vente"), rs.getString("pseudo"), rs.getInt("no_article"), rs.getString("nom_article"),rs.getString("description") , rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),rs.getInt("prix_initial") ,rs.getDate("date_enchere").toLocalDate() ,rs.getInt("montant_enchere") ,rs.getInt("id_user_enchere"),rs.getString("pseudo_best") ,rs.getInt("etat_vente") ,rs.getString("rue"), rs.getString("code_postal"),rs.getString("ville") );
				achatsRemportes.add(vente);
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
			
		return achatsRemportes;
	}

//UPDATE
	@Override
	public void updateEnchere(Integer dernierEncherisseur, Integer no_article,Integer nouvelEncherisseur, Integer montant_enchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 
			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(UPDATE_ENCHERE_SQL);
				pstmt.setInt(1, nouvelEncherisseur);
				pstmt.setInt(2, montant_enchere);
				pstmt.setInt(3, dernierEncherisseur);				
				pstmt.setInt(4, no_article);
	
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
