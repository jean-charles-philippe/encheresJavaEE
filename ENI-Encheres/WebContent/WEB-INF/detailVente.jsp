<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Détail Vente-ENI-Enchères</title>
</head>
<body>
<header class="container">
		<div class="jumbotron">
		  <h1 class="display-4"><a href="${pageContext.request.contextPath }/index">ENI-Enchères</a></h1>	  
		  <c:if test="${!empty userConnected }">
		  		<p>Bonjour ${userConnected.pseudo } !<br>
			  	<a href="${pageContext.request.contextPath }/index">Enchères</a>
				<a href="${pageContext.request.contextPath }/nouvelleVente">Vendre un article</a>
				<a href="${pageContext.request.contextPath }/modificationCompte">Mon profil</a>
				<a href="${pageContext.request.contextPath }/logout">Déconnexion</a>
				</p>
		  </c:if>
		  <hr class="my-4">
	  <!-- Affichage enregistrement -->
		  	<c:if test="${!empty enregistrementEnchere}">
		  		<div class="alert alert-warning" role="alert">
  					${enregistrementEnchere}
				</div>		  
		  	</c:if>
	  	
		  	
		  	
		  	
		</div>
	</header>

	<div class="container">
		<h2 class="text-center mb-5">
			<c:if test="${radioAchats == 1 }">Enchères ouvertes</c:if>
			<c:if test="${radioAchats == 2 }">Mes enchères en cours</c:if>
			<c:if test="${radioAchats == 3 }">Mes enchères remportées</c:if>
		</h2>
	
		<div class="row">

											
			<div class="col-md-3 ml-3 mb-3 text-center">
				<img src="images\icon-uploader-5.jpg" class="img-fluid rounded " alt="Responsive image">
			</div>
		
			<form action="encherir" method="POST" class=" col-md-7">
			
			<input type="hidden" value="${userConnected.id }" name="id_User_faisant_enchere">
			<input type="hidden" value="${enchere.id_user_enchere }" name="id_User_ayant_meilleur_offre_avant_enchere">
			<input type="hidden" value="${enchere.id_user_vente }" name="id_User_vendeur">			
			<input type="hidden" value="${enchere.no_article}" name="enchere_no_article">
			
			<div class="col-md-9">
				  	<div class="form-group row">
					    <label for="form_encherir_article" class="col-md-4 col-form-label">Article</label>
				        <div class="col-md-8">
				   			 <input type="text" class="form-control " id="form_encherir_article" name="form_encherir_article" value="${enchere.nomArticle}" readonly >	
		   				</div>
				 	</div>
				  
	
					<div class="form-group  row"">
				   		<label for="form_encherir_description" class="col-md-4	 col-form-label">Description</label>
				   		<div class="col-md-8">
				    		<textarea class="form-control" id="form_encherir_description" name="form_encherir_description" rows="1" readonly >${enchere.description}</textarea>
				    	</div>
				  	</div>
				  	

					
				  	<div class="form-group row">
					    <label for="form_encherir_prix" class="col-md-4 col-form-label">Mise à prix</label>
				        <div class="col-md-8">
				   			 <input type="text" class="form-control " id="form_encherir_prix" name="form_encherir_prix" value ="${enchere.miseAPrix} points" readonly >	
		   				</div>
				 	</div>												  
	
				  	<div class="form-group row">
					    <label for="form_encherir_offre" class="col-md-4 col-form-label">Meilleure offre</label>
				        <div class="col-md-8">
				   			 <input type="text" class="form-control " id="form_encherir_offre" name="form_encherir_offre" value ="${enchere.montant_enchere } pts par ${enchere.pseudo_best }" readonly >	
		   				</div>
				 	</div>	
				 						
					<div class="form-group  row mb-5 ">
					 	<label for="form_encherir_fin" class="col-md-4 col-form-label">Fin de l'enchère</label>
					 		<div class="col-md-8 ">
					 			<input type="text" class="form-control "  id="form_encherir_fin" name="form_encherir_fin" value ="${enchere.dateFinEncheres}" readonly >
					 		</div>
					</div>
				  			  
			
			 
			 
			 
		
					<div class="form-group  row"">
				   		<label for="form_encherir_Retrait" class="col-md-4 col-form-label">Retrait</label>
				   		<div class="col-md-8">
				    		<textarea class="form-control" id="form_encherir_Retrait" name="form_encherir_Retrait" rows="1" readonly >${enchere.rue } ${enchere.code_postal } ${enchere.ville  }</textarea>
				    	</div>
				  	</div>			  	
	    			  		
		
			  
			  		<div class="form-group row mb-5">
					    <label for="form_encherir_Vendeur" class="col-md-4 col-form-label">Vendeur</label>
				    	<div class="col-md-8">
				   			<input type="text" class="form-control " id="form_encherir_Vendeur" name="form_encherir_Vendeur" value="${enchere.pseudo}" readonly >	
		   				</div>
				 	</div>
				 	

				 	<div <c:if test="${(enchere.pseudo == userConnected.pseudo) || radioAchats == 3 }">class="d-none"</c:if>>
						<div class="form-group  row ">
						  	<label for="form_encherir_proposition" class="col-md-4 col-form-label">Ma proposition</label>
						  		<div class="col-md-8">	
						  			<input type="number" class="form-control" id="form_encherir_proposition" name="form_encherir_proposition" min="${enchere.montant_enchere + 1}" max="2000" value ="${enchere.montant_enchere}" autofocus
						  			<c:if test="${enchere.pseudo == userConnected.pseudo }"> </c:if>>
								</div> 
						</div>
							<div class="form-row justify-content-around col-md-11 " >
								<input type="submit" class="btn btn-primary btn-lg  form-group " value="Enchérir" name="encherir">			  		
					 		</div>				 	 
				 	 </div>				 	
			 </div>	 	
			  


			</form>				
		</div>
	
	</div>
</body>
</html>