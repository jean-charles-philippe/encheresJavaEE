<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Nouvelle vente - ENI-Enchères</title>
</head>
<body>
	<header class="container ">
		<div class="jumbotron">
		  <h1 class="display-4"><a href="${pageContext.request.contextPath }/index">ENI-Enchères</a></h1>
		  		<p>Bonjour ${userConnected.pseudo } !<br>
			  	<a href="${pageContext.request.contextPath }/index">Enchères</a>
				<a href="${pageContext.request.contextPath }/nouvelleVente">Vendre un article</a>
				<a href="${pageContext.request.contextPath }/modificationCompte">Mon profil</a>
				<a href="${pageContext.request.contextPath }/logout">Déconnexion</a>
				</p>
		  <hr class="my-4">
		</div>
	</header>
	
	<main class="container ">
		<h2 class="text-center mb-5">Nouvelle vente</h2>
	
		<div class="row">
		
			<div class="col-md-3 ml-3 mb-3 text-center">
				<img src="images\icon-uploader-5.jpg" class="img-fluid rounded " alt="Responsive image">
			</div>
		
			<form action="nouvelleVente" method="POST" class=" col-md-7">
			
			<div class="col-md-10">
					<input type="hidden" value="${userConnected.id }" name="form_nouvelleVente_id_Utilisateur_article">
				  	<div class="form-group row">
					    <label for="form_nouvelleVente_article" class="col-md-3 col-form-label">Article</label>
				        <div class="col-md-9">
				   			 <input type="text" class="form-control " id="form_nouvelleVente_article" name="form_nouvelleVente_article" autofocus required >	
		   				</div>
				 	</div>
				  
	
					<div class="form-group  row"">
				   		<label for="form_nouvelleVente_description" class="col-md-3 col-form-label">Description</label>
				   		<div class="col-md-9">
				    		<textarea class="form-control" id="form_nouvelleVente_description" name="form_nouvelleVente_description" rows="3"></textarea>
				    	</div>
				  	</div>
				  	
					<div class="form-group  row">
						<label  for="form_nouvelleVente_categorie" class="col-md-4 col-form-label">Catégorie</label>
				        <div class="col-md-8">						
							<select class="custom-select my-1 mr-sm-2" id="form_nouvelleVente_categorie" name="form_nouvelleVente_categorie" required>
								<option value="1" selected >Mobilier</option>
								<option value="2">Vêtements</option>
								<option value="3">Informatique</option>
							</select>
						</div>		    
					</div>
	
	
					  <div class="form-group row">
					    	<label for="form_nouvelleVente_uploader" class="col-md-4 col-form-label">Photo de l'article</label>
					    	<div class="col-md-8">	
					    		<input type="file" class="form-control-file" id="form_nouvelleVente_uploader" name="form_nouvelleVente_uploader">
					    	</div>
					  </div>
	
					
					<div class="form-group  row">
					  	<label for="form_nouvelleVente_prix" class="col-md-4 col-form-label">Mise à prix</label>
					  		<div class="col-md-8">	
					  			<input type="number" class="form-control" id="form_nouvelleVente_prix" name="form_nouvelleVente_prix" min="1" max="2000">
							</div> 
					</div>
												  
					<div class="form-group  row">
					 	<label for="form_nouvelleVente_debut" class="col-md-4 col-form-label">Début de l'enchère</label>
					 		<div class="col-md-8">
					 			<input type="date" class="form-control"  id="form_nouvelleVente_debut" name="form_nouvelleVente_debut" >
					 		</div>
					</div>
					
					<div class="form-group  row mb-5">
					 	<label for="form_nouvelleVente_fin" class="col-md-4 col-form-label">Fin de l'enchère</label>
					 		<div class="col-md-8">
					 			<input type="date" class="form-control"  id="form_nouvelleVente_fin" name="form_nouvelleVente_fin" required>
					 		</div>
					</div>
				  			  
			 </div>
			 
			 
			 
			  <fieldset class="form-group col-md-11 mb-5 ">
			  	<legend>Retrait</legend>
					<div class="form-group row">
						<label for="rue" class="col-md-3 col-form-label">Rue:</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="rue_form_nouvelleVente_rue" name="rue_form_nouvelleVente_rue" value="${userConnected.rue }" >
						</div>
					</div>
					<div class="form-group row">
						<label for="cp" class="col-md-3 col-form-label">Code Postal:</label>
							<div class="col-md-8">
						<input type="text" class="form-control" id="cp_form_nouvelleVente-cp" name="cp_form_nouvelleVente_cp" value="${userConnected.cp }" >
							</div>
					</div>	    
					<div class="form-group row">
						<label for="ville" class="col-md-3 col-form-label">Ville:</label>
							<div class="col-md-8">
						<input type="text" class="form-control" id="ville_form_nouvelleVente_ville" name="ville_form_nouvelleVente_ville" value="${userConnected.ville }" >
							</div>
					</div>		    			  		
			  </fieldset>
			  
			  <div class="form-row justify-content-around col-md-11 " >
					<input type="submit" class="btn btn-primary btn-lg  form-group " value="Enregistrer" name="nouvelleVente">			  		
			  		<input type="reset" class="btn btn-primary btn-lg  form-group " value="Annuler">

			  </div>
			</form>				
		</div>
	
			
	</main>
	
</body>
</html>