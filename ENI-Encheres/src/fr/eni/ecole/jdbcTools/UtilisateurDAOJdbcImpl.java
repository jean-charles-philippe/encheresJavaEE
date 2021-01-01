package fr.eni.ecole.jdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static final String INSERT_SQL = "INSERT INTO utilisateurs "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_LOGIN_SQL = "SELECT * "
			+ "FROM utilisateurs "
			+ "WHERE email=? AND mot_de_passe=?";
	
	private static final String SELECT_PROFIL_SQL = "SELECT * "
			+ "FROM utilisateurs "
			+ "WHERE pseudo=? ";
	
	private static final String SELECT_PROFIL_BY_ID_SQL = "SELECT * "
			+ "FROM utilisateurs "
			+ "WHERE no_utilisateur=? ";
	
	private static final String SELECT_MEMBRES = "SELECT * "
			+ "FROM utilisateurs ";
	
	
	private static final String UPDATE_PROFIL_SQL = "UPDATE utilisateurs "
			+ "SET pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?, credit=? "
			+ "WHERE no_utilisateur=?";	
	
	private static final String UPDATE_ACHETEUR_SQL = "	UPDATE UTILISATEURS \r\n" + 
			"						SET credit -= (SELECT prix_vente FROM ARTICLES WHERE etat_vente = 1 AND etat_enchere = 1 AND no_article=?)\r\n" + 
			"						FROM utilisateurs  \r\n" + 
			"						WHERE utilisateurs.no_utilisateur = ? ";
	
	private static final String UPDATE_VENDEUR_SQL = "UPDATE UTILISATEURS \r\n" + 
			"						SET credit += (SELECT prix_vente FROM ARTICLES WHERE etat_vente = 1 AND etat_enchere = 1 AND no_article=?)\r\n" + 
			"						FROM utilisateurs  \r\n" + 
			"						WHERE utilisateurs.no_utilisateur = ? ";
	
	
	private static final String DELETE_USER_SQL = "DELETE FROM utilisateurs "
			+ "WHERE no_utilisateur=?";
													

//INSERT
	@Override
	public void insertUtilisateur(Utilisateur user)  {
		Connection cnx = null;
		PreparedStatement pstmt = null; 

			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS); 
				pstmt.setString(1, user.getPseudo() );
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getTel());
				pstmt.setString(6, user.getRue());   
				pstmt.setString(7, user.getCp());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMdp());
				pstmt.setInt(10, user.getCredit());
				pstmt.setInt(11, user.isAdministrateur());
				
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
	public Utilisateur login(String email, String mdp) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		Utilisateur user= null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_LOGIN_SQL);
			pstmt.setString(1, email);
			pstmt.setString(2, mdp);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur") );
		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user= null;
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_PROFIL_SQL);
			pstmt.setString(1, pseudo);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur"));
			}			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return user;
	}
	
	@Override
	public Utilisateur selectById(Integer no_utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user= null;
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_PROFIL_BY_ID_SQL);
			pstmt.setInt(1, no_utilisateur);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur"));
			}			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return user;
	}	
	
	@Override
	public List<Utilisateur> selectMembres() {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user;
		List<Utilisateur> listMembres= new ArrayList<Utilisateur>();
		
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(SELECT_MEMBRES);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getInt("administrateur"));
				listMembres.add(user);
			}			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return listMembres;
	}
		
//UPDATE
	@Override
	public void updateUtilisateur(Utilisateur user) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 
			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(UPDATE_PROFIL_SQL);
				pstmt.setString(1, user.getPseudo());
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getTel());
				pstmt.setString(6, user.getRue());   
				pstmt.setString(7, user.getCp());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMdp());
				pstmt.setInt(10, user.getCredit());				
				pstmt.setInt(11, user.getId());
				
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
	public void crediterAcheteur(Integer acheteur, Integer no_article) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 
			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(UPDATE_ACHETEUR_SQL);
				pstmt.setInt(1, no_article);
				pstmt.setInt(2, acheteur);
						
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
	public void crediterVendeur(Integer vendeur, Integer no_article) {
		Connection cnx = null;
		PreparedStatement pstmt = null; 
			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(UPDATE_VENDEUR_SQL);
				pstmt.setInt(1, no_article);
				pstmt.setInt(2, vendeur);
		
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
	
//DELETE
	@Override
	public void deleteById(Integer id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = JdbcTools.getConnection();
			pstmt = cnx.prepareStatement(DELETE_USER_SQL);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
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
		}
		
		JdbcTools.closeConnection();
	}


	
}