<%@page import="fr.eni.ecole.encheres.bll.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<title>Accueil-ENI-Enchères</title>
</head>
<body class="justify-coentent-center">
	<header class="container ">
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
		  <c:if test="${empty userConnected }">
		  		<a href="${pageContext.request.contextPath }/login">S'inscrire - Se connecter</a>
		  </c:if>
		  <hr class="my-4">
<!-- Messages particuliers suite differentes actions-->
		  	<c:if test="${!empty pasDeVentesEnCours}">
		  		<div class="alert alert-warning" role="alert">
  					${pasDeVentesEnCours}
				</div>		  
		  	</c:if>
		  	<c:if test="${!empty PbCredit}">
		  		<div class="alert alert-warning" role="alert">
  					${PbCredit}
				</div>		  
		  	</c:if>		  	
		  	<c:if test="${!empty enregistrementEnchere}">
		  		<div class="alert alert-success" role="success">
  					${enregistrementEnchere}
				</div>		  
		  	</c:if>			  	
		  	<c:if test="${!empty EnchereTerminee}">
		  		<div class="alert alert-success" role="success">
  					${EnchereTerminee}
				</div>		  
		  	</c:if>			  	
		  	<c:if test="${!empty ventesNonDebutees}">
		  		<div class="alert alert-success" role="success">
  					${ventesNonDebutees}
				</div>		  
		  	</c:if>			  		  	
		  	<c:if test="${!empty ventesTerminees}">
		  		<div class="alert alert-success" role="success">
  					${ventesTerminees}
				</div>		  
		  	</c:if>
		  	<c:if test="${!empty insertedSell}">
		  		<div class="alert alert-success" role="success">
  					${insertedSell}
				</div>
			</c:if>	
		  	<c:if test="${!empty suppressionEnchere}">
		  		<div class="alert alert-success" role="success">
  					${suppressionEnchere}
				</div>
			</c:if>				
		  	<c:if test="${!empty mesEncheresEnCours}">
		  		<div class="alert alert-success" role="success">
  					${mesEncheresEnCours}
				</div>
			</c:if>				
		  	<c:if test="${!empty mesEncheresRemportees}">
		  		<div class="alert alert-success" role="success">
  					${mesEncheresRemportees}
				</div>
			</c:if>					  	
		</div>
	</header>
<!-- FILTRE GENERAL	 -->	
	<div class="container ">
		<h1 class="text-center">Liste des enchères</h1>
		<form action="index" method="POST" class="mb-5 justify-content-center">
			<fieldset class="row">
			 <legend class="ml-3">Filtres:</legend>
			 <div class="form col-12 col-md-6 mb-3">
				    <div class="col">
					  	<div class="form-group">
   							<input class="form-control " type="search" placeholder="Le nom de l'article contient ..." aria-label="Search" id="search_form_index"
   							<c:if test="${empty userConnected }">disabled</c:if>>	
 						</div>
				    </div>
				    			  
				    <div class="col mb-3">
						  <label class="my-1 mr-2" for="categorie">Catégorie</label>
						  <select class="custom-select my-1 mr-sm-2" id="filter_form_categorie"
						  	<c:if test="${empty userConnected }">disabled</c:if>>
						    <option value="1" selected>Mobilier</option>
						    <option value="2">Vêtements</option>
						    <option value="3">Informatique</option>
						  </select>		    
					</div>
						
							
<!-- FILTRE DES ACHATS	 -->				
					<c:if test="${!empty userConnected }">
						<div class="row  ">
							<div class="achats col-12  mb-3  ">
									<div class="form-check ">
									  <input class="form-check-input" type="radio" name="radioTop_form_categorie" id="radioTop_form_categorie" value="1"
									   	<c:if test="${(!empty userConnected && achatsVentes == 1)  }">checked</c:if>>
									  <label class="form-check-label" for="radioTop_form_categorie">Achats</label>
									</div>
								<div >
								    <div class="col">
								      <div class="form-check">
							        	<input class="form-check-input" type="radio" id="radioAchats" name="radioAchats" value="1"
							        	 	<c:if test="${(!empty userConnected && achatsVentes == 1 && radioAchats == 1 )  }">checked</c:if>>
								        <label class="form-check-label" for="radioAchats">
								          enchères ouvertes
								        </label>
								      </div>				
									</div>
								    <div class="col">
								      <div class="form-check">
							        	<input class="form-check-input" type="radio" id="radioAchats" name="radioAchats" value="2"
							        		<c:if test="${!empty userConnected && radioAchats == 2 && achatsVentes == 1 }">checked</c:if>>
								        <label class="form-check-label" for="radioAchats">
								          mes enchères en cours
								        </label>
								      </div>				
									</div>
								    <div class="col">
								      <div class="form-check">
							        	<input class="form-check-input" type="radio" id="radioAchats" name="radioAchats"  value="3"
							        		<c:if test="${!empty userConnected && radioAchats == 3 && achatsVentes == 1 }">checked</c:if>>
								        <label class="form-check-label" for="radioAchats">
								          mes enchères remportées
								        </label>
								      </div>
								    </div>     				
								</div>														
							</div>
							
<!-- FILTRE DES VENTES	 -->						
							<div class="ventes col-12   ">
								<div class="form-check ">
								  <input class="form-check-input" type="radio" name="radioTop_form_categorie" id="radioTop_form_categorie" value="2"
								  		<c:if test="${(!empty userConnected && achatsVentes == 2  )   }">checked</c:if>>
								  <label class="form-check-label" for="radioTop_form_categorie">Mes Ventes</label>
								</div>
								<div class="col-12">
							      <div class="form-check">
						        	<input class="form-check-input" type="radio" id="radioVentes" name="radioVentes" value="1"
						        		<c:if test="${!empty userConnected  && achatsVentes == 2 && radioVentes == 1 }">checked</c:if>>
							        <label class="form-check-label" for="radioVentes">
							          mes ventes en cours
							        </label>
							      </div>				
								</div>
							    <div class="col">
							      <div class="form-check">
						        	<input class="form-check-input" type="radio" id="radioVentes" name="radioVentes" value="2"
						        	<c:if test="${(!empty userConnected && achatsVentes == 2 && radioVentes == 2 )  }">checked</c:if>>
							        <label class="form-check-label" for="radioVentes">
							          ventes non débutées
							        </label>
							      </div>				
								</div>
							    <div class="col">
							      <div class="form-check">
						        	<input class="form-check-input" type="radio" id="radioVentes" name="radioVentes" value="3"
						        		<c:if test="${(!empty userConnected && achatsVentes == 2 &&  radioVentes == 3  )   }">checked</c:if>>
							        <label class="form-check-label" for="radioVentes">
							          ventes terminées
							        </label>
							      </div>				
								</div>								
							</div>			
						</div>
					</c:if>
			    </div>
					<div class="col col-12 col-md-5 offset-md-1  ">
				      <input type="submit" class="btn btn-primary btn-lg btn-block col-12 " value="Rechercher" name="rechercher_form_categorie"
						<c:if test="${empty userConnected }">disabled</c:if>>				       
				    </div>	
		  	</fieldset>
		</form>
	</div>
	
	
	
<!-- BLOC AFFICHAGE  ACHATS -->
	<div class="container ">
		<div class="row justify-content-around  mb-5 "> 
		  <c:if test="${empty userConnected || (!empty userConnected && achatsVentes == 1  ) }">	  		
			<c:forEach var="current" items="${mesAchats }">		
						<div class="card text-center mb-3 col-12 col-lg-6"  style="max-width: 540px;" >
				  			<div class=" row no-gutters">
				    			<div class="col-md-4  d-flex align-items-center justify-content-center ">
				      				<img src="images/icon-uploader-5.jpg" class="card-img   " alt="...">
				    			</div>
				    			<div class="col-md-8  ">
					      			<div class="card-body  ">
									   <h3 class="card-title">
								    	<c:choose>
								    		<c:when test="${!empty userConnected }">
								    				<a href="${pageContext.request.contextPath}/encherir?id=${current.no_article }">${current.nomArticle }</a>
								    		</c:when>
								    		<c:otherwise>
								    				${current.nomArticle }
								    		</c:otherwise>
								    	</c:choose>	</h3>					    
								    
										<p class="card-text">Mise à Prix: ${current.miseAPrix } points</p>
									    <p class="card-text">Fin de l'enchères: ${current.dateFinEncheres }</p>
									    <p class="card-text">Vendeur: 
								    	<c:choose>
								    		<c:when test="${!empty userConnected }">
								    				<a href="${pageContext.request.contextPath}/profil?pseudo=${current.pseudo }">${current.pseudo }</a>
								    		</c:when>
								    		<c:otherwise>
								    				${current.pseudo   }
								    		</c:otherwise>
								    	</c:choose>	</p>	    
					      			</div>
				    			</div>
				  			</div>
						</div>
			</c:forEach>					
	</c:if>
		 </div> 
	</div>		
	
	
<!-- BLOC AFFICHAGE VENTES -->
	<c:if test="${ !empty userConnected &&  achatsVentes == 2 }">							
		<c:forEach var="current" items="${mesVentes }" >
			<div class="container"> 
				<div class="row justify-content-around  mb-5">
					<div class="card mb-3 col-12 col-lg-6" style="max-width: 540px;">
			  			<div class="row no-gutters">
			    			<div class="col-md-4  d-flex align-items-center justify-content-center">
			      				<img src="images/icon-uploader-5.jpg" class="card-img  " alt="..." >
			    			</div>
			    			<div class="col-md-8">
				      			<div class="card-body">
								   <h3 class="card-title">
							    	<c:choose>
							    		<c:when test="${!empty userConnected }">
							    				<a href="${pageContext.request.contextPath}/consultationVentes?id=${current.no_article }">${current.nomArticle }</a>
							    		</c:when>
							    		<c:otherwise>
							    				${current.nomArticle }
							    		</c:otherwise>
							    	</c:choose>	</h3>					    
							    
									<p class="card-text">Mise à prix: ${current.miseAPrix } points</p>
								    <p class="card-text">Fin de l'enchère: ${current.dateFinEncheres }</p>
								    <p class="card-text">Vendeur: 
							    	<c:choose>
							    		<c:when test="${!empty userConnected }">
							    				<a href="${pageContext.request.contextPath}/profil?pseudo=${userConnected.pseudo}">${userConnected.pseudo }</a>
							    		</c:when>
							    		<c:otherwise>
							    				${userConnected.pseudo }
							    		</c:otherwise>
							    	</c:choose>	</p>
							    	<c:if test="${ !empty userConnected &&  current.etatVente == 1 }">
							    		<p class="card-text badge badge-success">VENDU pour ${current.prixVente } points</p>
							    	</c:if>	
    
				      			</div>
			    			</div>
			  			</div>
					</div>
				</div>
			</div>		
		</c:forEach>		
	</c:if>

</body>
</html>