<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Compte ENI-enchères</title>
</head>
<body>
<header class="container">
	<div class="jumbotron">
	  <h1 class="display-4">ENI-Enchères</h1>
	  <hr class="my-4">
	</div>
</header>


<div class="container">
	<h2 class="text-center mb-5">Mon Profil</h2>

	<form action="creationCompte" method="post" id="form_creation">
	  <div class="form-row">
	    <div class="col  col-12 col-md-5"> 
	      	<div class="form-group">
				<label for="pseudo">Pseudo:</label>
				<input type="text" class="form-control" id="pseudo_form_creation" name="pseudo_form_creation"  required pattern="[A-Za-z0-9]+" autofocus>
				<small id="pseudo_help" class="form-text text-muted">Caractères alphanumériques uniquement.</small>
			</div>
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				<label for="nom">Nom:</label>
				<input type="text" class="form-control" id="nom_form_creation" name="nom_form_creation" required>
			</div>	
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="prenom">Prénom:</label>
				 <input type="text" class="form-control" id="prenom_form_creation" name="prenom_form_creation" required>
			</div>	    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="email">Email:</label>
				 <input type="email" class="form-control" id="email_form_creation" name="email_form_creation" required>
			</div>	    
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="telephone">Téléphone:</label>
				 <input type="tel" class="form-control" id="telephone_form_creation" name="telephone_form_creation" pattern="[0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}" >
				 <small id="tel_help" class="form-text text-muted">06 54 67 .. ..</small>

			</div>		    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="rue">Rue:</label>
				 <input type="text" class="form-control" id="rue_form_creation" name="rue_form_creation" required>
			</div>
	    </div>
	  </div>
	  
	  <div class="form-row">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				 <label for="cp">Code Postal:</label>
				 <input type="text" class="form-control" id="cp_form_creation" name="cp_form_creation" required>
			</div>	    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				 <label for="ville">Ville:</label>
				 <input type="text" class="form-control" id="ville_form_creation" name="ville_form_creation" required>
			</div>		    
	    </div>
	  </div>
	  
	  <div class="form-row mb-3">
	    <div class="col  col-12 col-md-5">
			<div class="form-group">
				<label for="password">Mot de passe:</label>
				<input type="password" class="form-control" id="password_form_creation" name="password_form_creation" required>
			</div>	    
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2">
			<div class="form-group">
				<label for="password">Confirmation:</label>
				 <input type="password" class="form-control" id="password_form_creation_confirm" name="password_form_creation_confirm" required>
			</div>	    
	    </div>
	  </div>
	  
	  <div class="form-row mb-3">
	    <div class="col  col-12 col-md-5 text-center">
		
				<input type="submit" name="creationCompte"  value="Créer" class="btn btn-primary btn-lg">
		
	    </div>
	    <div class="col  col-12 col-md-5 offset-md-2 text-center">
				
				<input type="reset" class="btn btn-primary btn-lg">						
				    
	    </div>
	  </div>
	  	  	  	  
	</form>
	
	
</div>
</body>
</html>