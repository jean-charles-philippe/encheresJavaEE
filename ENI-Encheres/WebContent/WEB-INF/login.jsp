<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Login - ENI-Enchères</title>
</head>
<body>
	<header class="container">
		<div class="jumbotron">
		  <h1 class="display-4"><a href="${pageContext.request.contextPath }/index">ENI-Enchères</a></h1>
		  <hr class="my-4">
<!-- Affichage du message erreur de connexion -->
		  	<c:if test="${erreurLogin != null}">
		  		<div class="alert alert-warning" role="alert">
  					${erreurLogin}
				</div>
			</c:if>
		</div>
	</header>
	
	<main class="d-flex justify-content-center ">
		<form  action="login" method="POST"  class="col-12 col-md-6" >
			<div class="form-group">
    			<label for="email" class="col-form-label">Identifiant</label>
    			<div class="">
      				<input type="email" class="form-control" name="email_form_login" id="email_form_login"  value="${cookie.emailLogin.value }" autofocus>
    			</div>
    		</div>
    		
    		<div class="form-group">
				<label for="password">Mot de passe:</label>
				<div class="">
					<input type="password" class="form-control" name="password_form_login" id="password_form_login">
			    </div>
	    	</div>
	    	
	    	<div class="form-group row mb-5">
    			<div class="col-4">
      				<button type="submit" class="btn btn-primary">Connexion</button>
    			</div>
    			
			    <div class="col-7 offset-1">
			      <div class="form-check">
			        <input class="form-check-input" type="checkbox" id="check_souvenir" name="check_souvenir" value="1"
			       		<c:if test="${  cookie.suiviLogin.value == 1}">checked</c:if>
			        >
			        <label class="form-check-label" for="check_souvenir">
			          Se souvenir de moi
			        </label>  
			      </div>
			      <a href="#">Mot de passe oublié</a>
			    </div> 
			       				
  			</div>

			<a href="${pageContext.request.contextPath }/creationCompte">
				<button type="button" class="btn btn-primary btn-lg btn-block mb-5">Créer un compte</button>
			</a>
  
		</form>	
	</main>
	
	

</body>
</html>