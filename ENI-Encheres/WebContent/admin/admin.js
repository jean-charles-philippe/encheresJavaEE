
/**
 * 
 */
jQuery(
	function($){
		abonnement();
		
		function abonnement(){
			//$("#encheresEnCours_membre").hide();
			
			$("#lister").click(function(){lister()});
		}
	}
)




function createXHR() {
	if (window.XMLHttpRequest) // Objet standard
	{
	xhr = new XMLHttpRequest(); // Firefox, Safari, ...
	}
	else if (window.ActiveXObject) // Internet Explorer
	{
	xhr = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return xhr;
	}


//fonction qui récupère les membres de l'application
	function lister()
	{
		var xhr = createXHR();
		xhr.onreadystatechange = function()
			{
			if (xhr.readyState == 4)
			{
			if (xhr.status == 200)
			{
				var reponse = xhr.responseText;//xhr.responseXML si réponse XML
				var members = document.getElementById("members");
				members.innerHTML= "";
				const obj = JSON.parse(reponse);
				
				for (let elt of obj) {
					var ligne = document.createElement("tr");
					var id = document.createElement("th");
					var nom = document.createElement("td");
					var prenom = document.createElement("td");
					var email = document.createElement("td");
					var suppression = document.createElement("td");
					var desactivation = document.createElement("td");
					var supprimer = document.createElement("input");
					var suspendre = document.createElement("input");
					
					ligne.setAttribute("onclick" , "select(this)" );
					id.innerText = elt.id;
					nom.innerText = elt.nom;
					prenom.innerText = elt.prenom;
					email.innerText = elt.email;
					supprimer.setAttribute("type", "button");
					supprimer.setAttribute("value", "Supprimer");
					supprimer.setAttribute("onclick", "supprimer()")
					supprimer.className = "btn btn-warning";
					suspendre.setAttribute("type", "button");
					suspendre.setAttribute("value", "Suspendre");
					suspendre.setAttribute("onclick", "suspendre()")
					suspendre.className = "btn btn-warning";
					
					
					members.appendChild(ligne);
					ligne.appendChild(id);
					ligne.appendChild(nom);
					ligne.appendChild(prenom);
					ligne.appendChild(email);
					ligne.appendChild(supprimer);
					ligne.appendChild(suspendre);
				
				}	
			}
			else
				{
					echec(xhr.status, xhr.responseText);
				}
			}
				};
				
		xhr.open("GET", "/ENI-Encheres/rest/administrateur", false);
		xhr.setRequestHeader("Accept","application/json");
		xhr.send(null);
	
	}
	
//fonction qui selectionne un membre dans le tableau membres et retourne son numero	
	function select(event){
	
	var elt = document.querySelector(".selected");
	if (elt != null) {
		elt.className = "";
	}	
	event.className = "selected";
	 id_membre = event.firstElementChild.innerText;
	
	console.log(id_membre);
	selectVEC(id_membre);
	afficherEncheres_membre();
	}
	
//fonction qui récupère les ventes en cours d'un membre
	function selectVEC(id_membre)
	{
		var xhr = createXHR();
		xhr.onreadystatechange = function()
			{
			if (xhr.readyState == 4)
			{
			if (xhr.status == 200)
			{
				var reponse = xhr.responseText;//xhr.responseXML si réponse XML				
				var vec = document.getElementById("vec");
				vec.innerHTML= "";
				const obj = JSON.parse(reponse);
				
				for (let elt of obj) {
					var ligne = document.createElement("tr");
					var id = document.createElement("th");
					var nom_article = document.createElement("td");
					var dateFinEncheres = document.createElement("td");
					id.innerText = elt.no_article;
					nom_article.innerText = elt.nomArticle;
					dateFinEncheres.innerText = elt.dateFinEncheres;
					
					console.log(elt.dateFinEncheres);
	/*					var day = elt.dateFinEncheres.dayOfMonth;
					var month = elt.dateFinEncheres.monthValue;
					var year = elt.dateFinEncheres.year;
					
					
				console.log(day);
					console.log(month);
					console.log(year);
					var date = new Date();
					date.setDate(day);
					date.setMonth(month);
					date.setYear(year);
					dateFinEncheres = date;
					console.log(date.toLocaleString('fr-FR'));
					dateFinEncheres.innerText = date.toLocaleString('fr-FR')*/
					
					vec.appendChild(ligne);
					ligne.appendChild(id);
					ligne.appendChild(nom_article);
					ligne.appendChild(dateFinEncheres);
					
					
				}	
			}
			else
				{
					echec(xhr.status, xhr.responseText);
				}
			}
				};
				
				
		$("#encheresEnCours_membre").load("contenu.html #AchatsEtVentes_membre");
		xhr.open("GET", `/ENI-Encheres/rest/administrateur/${id_membre}`, false);
		xhr.setRequestHeader("Accept","application/json");
		xhr.send(null);
	}	

	function afficherEncheres_membre(){
		$("#encheresEnCours_membre").show();
		
	}
	

	
	
	function echec(codeReponse, reponse)
	{
		document.getElementById("echec").innerHTML=reponse;
		document.getElementById("members").innerHTML="";
	}
