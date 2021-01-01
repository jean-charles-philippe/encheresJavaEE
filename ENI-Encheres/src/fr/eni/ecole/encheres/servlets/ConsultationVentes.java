package fr.eni.ecole.encheres.servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.VenteManager;

/**
 * Servlet implementation class ConsultationVentes
 */
@WebServlet("/consultationVentes")
public class ConsultationVentes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /*
     * Chemin dans lequel les images seront sauvegardées.
     */
    public static final String IMAGES_FOLDER = "/imagesVentes";    
    public String uploadPath;
    
    
    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */  
	@Override
	public void init() throws ServletException {
	       uploadPath = getServletContext().getRealPath( IMAGES_FOLDER );
	        File uploadDir = new File( uploadPath );
	        if ( ! uploadDir.exists() ) uploadDir.mkdir();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Article article_a_MAJ;
		request.setCharacterEncoding("UTF-8");
		Integer no_article =Integer.parseInt(request.getParameter("id"));
		VenteManager mngr = VenteManager.getInstance();
		article_a_MAJ = mngr.selectArticleByNo_article(no_article);	
		
//		String imageVente = article_a_MAJ.getImageVente();
//		imageVente = "imagesVentes\\".concat(imageVente);
//		article_a_MAJ.setImageVente(imageVente);	
		
		session.setAttribute("article_a_MAJ", article_a_MAJ);
		System.out.println(article_a_MAJ.getImageVente());
		getServletContext().getRequestDispatcher("/WEB-INF/mesVenteEnCours.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer no_article = Integer.parseInt((String) request.getParameter("id_article_aModifier"));
		VenteManager mngr = VenteManager.getInstance();
		HttpSession session = request.getSession();
		if (request.getParameter("annulerVente") != null) {
			Utilisateur user = (Utilisateur)(session.getAttribute("userConnected"));	
			List<Article> ventesNonDebutees;
			mngr.deleteArticle_Enchere(no_article);
			request.setAttribute("suppressionEnchere", "Votre enchère est bien supprimée.");
			ventesNonDebutees = mngr.selectArticleByUser_vND(user.getId());
			session.setAttribute("mesVentes", ventesNonDebutees);
			getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			return;
		}
		
		Article article_a_MAJ;
		request.setCharacterEncoding("UTF-8");		
		
		String nomArticle = request.getParameter("form_consultationVentes_article").toUpperCase();
		String description = request.getParameter("form_consultationVentes_description");
		LocalDate dateDebutEncheres = LocalDate.parse(request.getParameter("form_consultationVentes_debut"));
		LocalDate dateFinEncheres = LocalDate.parse(request.getParameter("form_consultationVentes_fin"));
		Integer miseAPrix = Integer.parseInt(request.getParameter("form_consultationVentes_prix"));
		Integer prixVente = null;
		Integer categorie = Integer.parseInt(request.getParameter("form_consultationVentes_categorie"));
		Integer etatVente = 0;
		Integer id_utilisateur = Integer.parseInt(request.getParameter("id_Utilisateur_recuperation_article"));
		String rue = request.getParameter("rue_form_consultationVentes_rue");
		String code_postal = request.getParameter("cp_form_consultationVentes_cp");
		String ville = request.getParameter("ville_form_consultationVentes_ville");
		
		String fileName = null;
		String imageVente = null;
//upload		
        for ( Part part : request.getParts() ) {
            fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
            if (!fileName.equals("Default.file")) {
            	imageVente = fileName;
			}
            
        }

		article_a_MAJ = new Article(no_article, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, categorie, etatVente, id_utilisateur, rue, code_postal, ville, imageVente);
		mngr.updateArticleEnVente(article_a_MAJ);
		
		article_a_MAJ = mngr.selectArticleByNo_article(no_article);		
		session.setAttribute("article_a_MAJ", article_a_MAJ);
		request.setAttribute("articleUpdated", "Votre modification a bien été prise en compte.");
		getServletContext().getRequestDispatcher("/WEB-INF/mesVenteEnCours.jsp").forward(request, response);
	}
	
	//upload	
	   /*
  * Récupération du nom du fichier dans la requête.
  */
 private String getFileName( Part part ) {
     for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
         if ( content.trim().startsWith( "filename" ) )
             return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
         	
     } 
     return "Default.file";
     
 }

}
