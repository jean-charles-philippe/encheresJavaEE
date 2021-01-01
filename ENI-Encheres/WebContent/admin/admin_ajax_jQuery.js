
/**
 * 
 */

jQuery(
	function($){
		abonnement();
		
		function abonnement(){
			//$("#encheresEnCours_membre").hide();
			URL = "/ENI-Encheres/rest/administrateur";
			$("#lister").click(function(){lister()});
		}
	}
)



//fonction qui récupère les membres de l'application
	function lister()
	{
		$.ajax(
			{
				url: URL,
				method: "GET",
				
			})
			.done(function(membresAAfficher){
				/*console.log(membresAAfficher);*/
				$("#members").empty();
				membresAAfficher.forEach(
					function(membre){
						var ligne = $("<tr></tr>");
						ligne.appendTo("tbody");
						var id = $("<th/>",
											{html: membre.id});
						var nom = $("<th/>",
											{html: membre.nom});
						var prenom = $("<th/>",
											{html: membre.prenom});											
						var email = $("<th/>",
											{html: membre.email});											

						id.appendTo("tr:last");
						nom.appendTo("tr:last");
						prenom.appendTo("tr:last");
						email.appendTo("tr:last");
					}
				)	
		

			})	
				
		}
	
	
				
	
	


	
	
