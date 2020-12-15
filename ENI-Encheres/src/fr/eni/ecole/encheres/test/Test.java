package fr.eni.ecole.encheres.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.Enchere;
import fr.eni.ecole.encheres.bll.VenteManager;

public class Test {

	public static void main(String[] args) {

		List<Enchere> mesEncheresEnCours = new ArrayList<Enchere>();
		VenteManager mngr = VenteManager.getInstance();
		mesEncheresEnCours = mngr.selectEnchereSuivie(1010);
	
			
			System.out.println(mesEncheresEnCours);
			
		}
	

}
