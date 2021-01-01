package fr.eni.ecole.encheres.test;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.encheres.bll.Article;
import fr.eni.ecole.encheres.bll.VenteManager;

public class Test {

	public static void main(String[] args) {

		List<Article> list = new ArrayList<Article>();
		VenteManager mngr = VenteManager.getInstance();
		list = mngr.selectArticleByUser(1010);
		
			
			System.out.println(list);
			
		}

	

	
}
