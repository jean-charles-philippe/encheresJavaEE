<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<title>Mon Profil-ENI-Enchères</title>
	</head>
<body>
	<header class="container">
		<div class="jumbotron">
		  <h1 class="display-4"><a href="${pageContext.request.contextPath }/index">ENI-Enchères</a></h1>
		  		<p>Bonjour ${userConnected.pseudo } !<br>
			  	<a href="${pageContext.request.contextPath }/index">Enchères</a>
				<a href="${pageContext.request.contextPath }/nouvelleVente">Vendre un article</a>
				<a href="${pageContext.request.contextPath }/modificationCompte">Mon profil</a>
				<a href="${pageContext.request.contextPath }/logout">Déconnexion</a>
				</p>
		  <hr class="my-4">
	<!-- Affichage de mise à jour de connexion -->
		  	<c:if test="${!empty updateProfile}">
		  		<div class="alert alert-warning" role="alert">
  					${updateProfile}
				</div>
			</c:if>
		</div>
	</header>
	
	
<div class="container">
	<h2 class="text-center mb-5">Mon Profil</h2>

	<form action="modificationCompte" method="post" id="form_modification">
	<input id="idUpdate" name="idUpdate" type="hidden" value="${userConnected.id }">
	<input id="credit" name="credit" type="hidden" value="${userConnected.credit }">
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
	      	<div class="form-group">
				<label for="pseudo">Pseudo:</label>
				<input type="text" class="form-control" id="pseudo_form_modification_pseudo" name="pseudo_form_modification_pseudo"  required pattern="[A-Za-z0-9]+" value="${userConnected.pseudo }" >
				<small id="pseudo_help" class="form-text text-muted">Caractères alphanumériques uniquement.</small>
			</div>
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				<label for="nom">Nom:</label>
				<input type="text" class="form-control" id="nom_form_modification_nom" name="nom_form_modification_nom" value="${userConnected.nom }" >
			</div>	
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="prenom">Prénom:</label>
				 <input type="text" class="form-control" id="prenom_form_modification_prenom" name="prenom_form_modification_prenom" value="${userConnected.prenom }" >
			</div>	    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="email">Email:</label>
				 <input type="email" class="form-control" id="email_form_modification_email" name="email_form_modification_email" value="${userConnected.email }" >
			</div>	    
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="telephone">Téléphone:</label>
				 <input type="tel" class="form-control" id="telephone_form_modification_tel" name="telephone_form_modification_tel" pattern="[0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}" value="${userConnected.tel}">
				 <small id="tel_help" class="form-text text-muted">06 54 67 .. ..</small>

			</div>		    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="rue">Rue:</label>
				 <input type="text" class="form-control" id="rue_form_modification_rue" name="rue_form_modification_rue" value="${userConnected.rue }" >
			</div>
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="cp">Code Postal:</label>
				 <input type="text" class="form-control" id="cp_form_modification-cp" name="cp_form_modification_cp" value="${userConnected.cp }" >
			</div>	    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="ville">Ville:</label>
				 <input type="text" class="form-control" id="ville_form_modification_ville" name="ville_form_modification_ville" value="${userConnected.ville }" >
			</div>		    
	    </div>
	  </div>
	  
	  <div class="form-row">
		    <div class="col  col-12 col-md-5">
				<div class="form-group">
					<label for="password">Mot de passe actuel:</label>
					<input type="password" class="form-control" id="password_form_modification_current" name="password_form_modification_current" value="${userConnected.mdp}" readonly>
				</div>	    
		   	</div>

	   		<div class="col  col-12 col-md-8 "></div>
	  </div>


	  
	  <div class="form-row mb-3 ">
		    <div class="col  col-12 col-md-5">
				<div class="form-group">
					<label for="password">Nouveau mot de passe:</label>
					<input type="password" class="form-control" id="password_form_modification_new" name="password_form_modification_new" >
				</div>	    
			</div>	  
	  		<div class="col  col-12 col-md-5 offset-md-2">
				<div class="form-group">
					<label for="password">Confirmation:</label>
					<input type="password" class="form-control" id="password_form_modification_new_confirmation" name="password_form_modification_new_confirmation" >
				</div>	    
	   		</div>
	  </div>
	    
		<h3>Crédit <span class="badge badge-secondary mb-3">${userConnected.credit}</span></h3>	    	  
	  
		<div class="form ">
		    <div class="col text-center mb-3 ">		
					<input type="submit" name="modificationCompte"  value="Modifier" class="btn btn-primary btn-lg">		
		    </div>	    
		</div>
	  	  	  	  
	</form>
	
	
	<div class="col text-center mb-5 ">
	    	<form action="deleteUser" method="POST" >
	    			<input type="submit"  name="delete_form_modification" value="Supprimer le compte"  class="btn btn-warning btn-lg">
	    			<input name="Iddelete_form_modification" type="hidden" value="${userConnected.id }">
	    	</form>
	</div>
	
</div>

</body>
</html>