package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.RetrievePizzaSQLException;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws RetrievePizzaSQLException {
		System.out.println("Lister les pizzas");
		for (Pizza pizza: dao.findAllPizzas()){
			System.out.println(pizza);
		}

	}

}
