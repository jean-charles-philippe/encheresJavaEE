<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Profil Membre-ENI-Enchères</title>
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
		</div>
	</header>
	
	
		<main class="d-flex justify-content-center">
			<table class="table table-borderless table-sm col-md-6">
			  <tbody>
			    <tr>
			      <th scope="row" class="text-center">Pseudo :</th>
			      <td class="text-center">${profil.pseudo }</td>

			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Nom :</th>
			      <td class="text-center">${profil.nom }</td>

			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Prénom :</th>
			      <td class="text-center">${profil.prenom }</td>
			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Email :</th>
			      <td class="text-center">${profil.email }</td>

			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Téléphone :</th>
			      <td class="text-center">${profil.tel}</td>

			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Rue :</th>
			      <td class="text-center">${profil.rue }</td>
			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Code postal :</th>
			      <td class="text-center">${profil.cp }</td>

			    </tr>
			    <tr>
			      <th scope="row" class="text-center">Ville :</th>
			      <td class="text-center">${profil.ville }</td>
			    </tr>
			  </tbody>
			</table>	
	</main>
</body>
</html>