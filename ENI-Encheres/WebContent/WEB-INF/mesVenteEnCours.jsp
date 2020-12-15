<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Mes Ventes en cours-ENI-Enchères</title>
</head>
<body>
<header class="container">
		<div class="jumbotron">
		  <h1 class="display-4">ENI-Enchères</h1>		  
		  <c:if test="${!empty userConnected }">
		  		<p>Bonjour ${userConnected.pseudo } !<br>
			  	<a href="${pageContext.request.contextPath }/index">Enchères</a>
				<a href="${pageContext.request.contextPath }/nouvelleVente">Vendre un article</a>
				<a href="${pageContext.request.contextPath }/modificationCompte">Mon profil</a>
				<a href="${pageContext.request.contextPath }/logout">Déconnexion</a>
				</p>
		  </c:if>
		  <hr class="my-4">
  		  	<c:if test="${!empty suppressionEnchere}">
  				<div class="alert alert-success" role="success">
					${suppressionEnchere}
				</div>
			</c:if>	
  		  	<c:if test="${!empty articleUpdated}">
  				<div class="alert alert-success" role="success">
					${articleUpdated}
				</div>
			</c:if>			
			
			
		</div>
	</header>

<!-- BLOC AFFICHAGE DU FILTRE MES VENTES EN COURS-->
	<c:if test="${!empty userConnected  }">
		<div class="container">
			<h2 class="text-center mb-5">
			<c:if test="${radioVentes == 1 }">Vente en cours</c:if>
			<c:if test="${radioVentes == 2 }">Vente non débutée</c:if>
			<c:if test="${radioVentes == 3 }">Vente terminée</c:if>
			</h2>
		
			<div class="row">

												
				<div class="col-md-3 ml-3 mb-3 text-center">
					<img src="images\icon-uploader-5.jpg" class="img-fluid rounded " alt="Responsive image">
				</div>
			
				<form action="consultationVentes" method="POST" class=" col-md-7">
					<input type="hidden" value="${userConnected.id }" name="id_Utilisateur_recuperation_article">
					<input type="hidden" value="${article_a_MAJ.no_article }" name="id_article_aModifier">			

				
				<div class="col-md-10">
					  	<div class="form-group row">
						    <label for="form_consultationVentes_article" class="col-md-4 col-form-label">Article</label>
					        <div class="col-md-8">
					   			 <input type="text" class="form-control " id="form_consultationVentes_article" name="form_consultationVentes_article" value="${article_a_MAJ.nomArticle}"
					   			  <c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if> autofocus required >	
			   				</div>
					 	</div>
					  
		
						<div class="form-group  row"">
					   		<label for="form_consultationVentes_description" class="col-md-4 col-form-label">Description</label>
					   		<div class="col-md-8">
					    		<textarea class="form-control" id="form_consultationVentes_description" name="form_consultationVentes_description" rows="3"
					    		<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if> >${article_a_MAJ.description}</textarea>
					    	</div>
					  	</div>
					  	
						<div class="form-group  row">
							<label  for="form_consultationVentes_categorie" class="col-md-4 col-form-label">Catégorie</label>
					        <div class="col-md-8">						
								<select class="custom-select my-1 mr-sm-2" id="form_consultationVentes_categorie" name="form_consultationVentes_categorie"
								<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if>  required>
								
									<option value ="1"
									 <c:if test="${article_a_MAJ.categorie  == 1 }">selected</c:if> >Mobilier</option>
									<option value ="2"
									<c:if test="${article_a_MAJ.categorie  == 2 }">selected</c:if> >Vêtements</option>
									<option value ="3"
									<c:if test="${article_a_MAJ.categorie  == 3 }">selected</c:if> >Informatique</option>
								</select>
							</div>		    
						</div>
		
		
						  <div class="form-group row
						   <c:if test="${radioVentes == 3 || radioVentes == 1 }">d-none</c:if>">
						    	<label for="form_consultationVentes_uploader" class="col-md-4 col-form-label">Photo de l'article</label>
						    	<div class="col-md-8">	
						    		<input type="file" class="form-control-file" id="form_consultationVentes_uploader" name="form_consultationVentes_uploader">
						    	</div>
						  </div>
		
						
						<div class="form-group  row">
						  	<label for="form_consultationVentes_prix" class="col-md-4 col-form-label">Mise à prix</label>
						  		<div class="col-md-8">	
						  			<input type="number" class="form-control" id="form_consultationVentes_prix" name="form_consultationVentes_prix" min="1" max="2000" value ="${article_a_MAJ.miseAPrix}"
						  			<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if>>
								</div> 
						</div>
													  
						<div class="form-group  row">
						 	<label for="form_consultationVentes_debut" class="col-md-4 col-form-label">Début de l'enchère</label>
						 		<div class="col-md-8">
						 			<input type="date" class="form-control"  id="form_consultationVentes_debut" name="form_consultationVentes_debut" value ="${article_a_MAJ.dateDebutEncheres}" 
						 			<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if>>
						 		</div>
						</div>
						
						<div class="form-group  row mb-5">
						 	<label for="form_consultationVentes_fin" class="col-md-4 col-form-label">Fin de l'enchère</label>
						 		<div class="col-md-8">
						 			<input type="date" class="form-control"  id="form_consultationVentes_fin" name="form_consultationVentes_fin" value ="${article_a_MAJ.dateFinEncheres}" 
						 			<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if>>
						 		</div>
						</div>
					  			  
				 </div>
				 
				 
				 
				  <fieldset class="form-group col-md-10 mb-5 ">
				  	<legend>Retrait</legend>
						<div class="form-group row">
							<label for="rue" class="col-md-4 col-form-label">Rue:</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="rue_form_consultationVentes_rue" name="rue_form_consultationVentes_rue" value="${article_a_MAJ.rue }"
								<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if> >
							</div>
						</div>
						<div class="form-group row">
							<label for="cp" class="col-md-4 col-form-label">Code Postal:</label>
								<div class="col-md-8">
							<input type="text" class="form-control" id="cp_form_consultationVentes-cp" name="cp_form_consultationVentes_cp" value="${article_a_MAJ.code_postal }"
							<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if> >
								</div>
						</div>	    
						<div class="form-group row">
							<label for="ville" class="col-md-4 col-form-label">Ville:</label>
								<div class="col-md-8">
							<input type="text" class="form-control" id="ville_form_consultationVentes_ville" name="ville_form_consultationVentes_ville" value="${article_a_MAJ.ville  }"
							<c:if test="${radioVentes == 3 || radioVentes == 1 }">disabled</c:if> >
								</div>
						</div>		    			  		
				  </fieldset>
				  
				  
				  <div class="form-row justify-content-around col-md-11
				  <c:if test="${radioVentes == 3 || radioVentes == 1 }">d-none</c:if> " >
						<input type="submit" class="btn btn-primary btn-lg  form-group " value="Enregistrer" name="modifierVentes">			  		
				  		<input type="reset" class="btn btn-primary btn-lg  form-group " value="Annuler">
						<input type="submit" class="btn btn-primary btn-lg  form-group " value="Annuler la vente" name="annulerVente">
				  </div>
				</form>				
			</div>
		
		</div>
	</c:if>

</body>
</html>