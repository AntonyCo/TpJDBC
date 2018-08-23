package fr.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.SavePizzaSQLException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.AjouterPizzaService;


public class AjouterPizzaServiceTest {
	private List<Pizza> pizzas = new ArrayList<>();
	
	public AjouterPizzaServiceTest(){
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
	}
	
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	@Test
	public void testExecuteUC() throws SavePizzaSQLException{
		systemInMock.provideLines("POI", "Poivrons", "12,5");
		AjouterPizzaService aps = new AjouterPizzaService();
		PizzaMemDao dao = new PizzaMemDao();
		aps.executeUC(new Scanner(System.in), dao);
		
		List<Pizza> expected = pizzas;
		expected.add(new Pizza("POI", "Poivrons", 12.5d));
		
		Assert.assertEquals(expected, dao.getPizzas());
	}
}