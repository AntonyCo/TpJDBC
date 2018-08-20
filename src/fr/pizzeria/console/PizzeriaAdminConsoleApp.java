package fr.pizzeria.console;

import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaBaseDao;
import fr.pizzeria.exceptions.DriverMySQLException;
import fr.pizzeria.exceptions.PropertiesException;
import fr.pizzeria.exceptions.StorageSQLException;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {
	
	public static void main(String[] args) {
		
		
		/* Initialisation du TDB de pizzas */
		IPizzaDao dao = null;
		try {
			dao = new PizzaBaseDao();
		} catch (PropertiesException e1) {
			e1.printStackTrace();
		} catch (DriverMySQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/* Initialisation du scanner */
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		/* Structure de répétition de la boucle principale */
		do {
			println("***** Pizzeria Administration *****");
			println("1. Lister les pizzas ");
			println("2. Ajouter une nouvelle pizza ");
			println("3. Mettre à jour une pizza ");
			println("4. Supprimer une pizza ");
			println("10. Initialiser le menu à partir du tableau de pizzas ");
			println("99. Sortir ");
	
			System.out.print("Veuillez choisir une option:");
			choix = scanner.nextInt();
			
			MenuService service = MenuServiceFactory.getInstance(choix);
			try {
				service.executeUC(scanner, dao);
			} catch (StorageSQLException e) {
				e.printStackTrace();
			}
			
		} while(choix!=99);
		
		scanner.close();
		
	}
	
	

	private static void println(String msg){
		System.out.println(msg);
	}
}
