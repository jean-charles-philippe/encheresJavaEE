package fr.eni.ecole.jdbcTools;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import fr.eni.ecole.encheres.bll.Retrait;
import fr.eni.ecole.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	private static final String INSERT_RETRAIT_SQL = "INSERT INTO retraits (no_article,rue, code_postal,ville) "
			+ "VALUES (?,?,?,?)";

	
//INSERT	
	@Override
	public void insertRetrait(Retrait retrait) {
			Connection cnx =null;
			PreparedStatement pstmt = null;
			
			try {
				cnx = JdbcTools.getConnection();
				pstmt = cnx.prepareStatement(INSERT_RETRAIT_SQL);
				pstmt.setInt(1, retrait.getId());
				pstmt.setString(2, retrait.getRue());
				pstmt.setString(3, retrait.getCode_postal());
				pstmt.setString(4, retrait.getVille());
				
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


