package fr.eni.ecole.encheres.rest;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.UtilisateurManager;
import fr.eni.ecole.encheres.bll.VenteManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

@Path("/administrateur")
public class Administrateur {
List<Utilisateur>ListMembres = new ArrayList<Utilisateur>();
List<Article>ListVentesEC = new ArrayList<Article>();

	@GET
	public List<Utilisateur> getMembres() {
		UtilisateurManager mngr = UtilisateurManager.getInstance();
		ListMembres = mngr.selectMembres();
		return ListMembres ;
	}
	
	@GET
	@Path("/{id_membre : \\d+}")
	public List<Article> selectArticlesVEC(@PathParam("id_membre") int id_membre) {
		VenteManager mngr = VenteManager.getInstance();
		ListVentesEC = mngr.selectArticleByUser(id_membre);
		return ListVentesEC ;
	}
	
}
