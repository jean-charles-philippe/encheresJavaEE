package fr.eni.ecole.encheres.servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Retrait;
import fr.eni.ecole.encheres.bll.Utilisateur;
import fr.eni.ecole.encheres.bll.VenteManager;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/nouvelleVente")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5 )
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
    /*
     * Chemin dans lequel les images seront sauvegardées.
     */
    public static final String IMAGES_FOLDER = "imagesVentes";    
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

		getServletContext().getRequestDispatcher("/WEB-INF/nouvelleVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String nomArticle = request.getParameter("form_nouvelleVente_article");
		String description  = request.getParameter("form_nouvelleVente_description");
		LocalDate dateDebutEncheres  = LocalDate.parse(request.getParameter("form_nouvelleVente_debut"));
		LocalDate dateFinEncheres  = LocalDate.parse(request.getParameter("form_nouvelleVente_fin"));
		Integer miseAPrix = Integer.parseInt(request.getParameter("form_nouvelleVente_prix"));
		Integer categorie = Integer.parseInt(request.getParameter("form_nouvelleVente_categorie"));

		String rue = request.getParameter("rue_form_nouvelleVente_rue");
		String code_postal = request.getParameter("cp_form_nouvelleVente_cp");
		String ville = request.getParameter("ville_form_nouvelleVente_ville");
		Integer etatVente = 0;
		Integer id_utilisateur = Integer.parseInt(request.getParameter("form_nouvelleVente_id_Utilisateur_article"));
		List<Article> mesVentesEnCours;
		List<Article> ventesNonDebutees;
		String fileName = null;
		String imageVente = null;
//upload		
        for ( Part part : request.getParts() ) {
            fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
            if (!fileName.equals("Default.file")) {
            	imageVente = IMAGES_FOLDER + "\\" + fileName;
			}
            
        }
		
		
		
		Article article = new Article(nomArticle.toUpperCase(), description, dateDebutEncheres, dateFinEncheres, miseAPrix, categorie, etatVente, id_utilisateur, imageVente );
		Utilisateur user = (Utilisateur)(session.getAttribute("userConnected"));
	
		VenteManager mngr = VenteManager.getInstance();
		
		Integer id = mngr.insertArticle(article);

		Retrait retrait = new Retrait(id  ,rue, code_postal, ville.toUpperCase());
		mngr.insertRetrait(retrait);
		request.setAttribute("insertedSell", "Votre objet a bien été enregistré.");
		mngr.creationEnchere(id_utilisateur, id, dateDebutEncheres, miseAPrix );

		
		if (LocalDate.now().isAfter((article.getDateDebutEncheres().minusDays(1)))  && (LocalDate.now().isBefore(article.getDateFinEncheres()))) {
			mesVentesEnCours =  mngr.selectArticleByUser(user.getId());
			String radioVentes = "1";
			session.setAttribute("achatsVentes", "2");		
			session.setAttribute("radioVentes", radioVentes);
			session.setAttribute("mesVentes", mesVentesEnCours);
			
		}
		else if (LocalDate.now().isBefore((article.getDateDebutEncheres()))) {
			ventesNonDebutees = mngr.selectArticleByUser_vND(user.getId());
			session.setAttribute("achatsVentes", "2");	
			session.setAttribute("radioVentes", "2");
			session.setAttribute("mesVentes", ventesNonDebutees);

		}


		getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
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
