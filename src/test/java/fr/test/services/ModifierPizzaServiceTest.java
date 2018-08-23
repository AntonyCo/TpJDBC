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
import fr.pizzeria.exceptions.UpdatePizzaSQLException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.ModifierPizzaService;

public class ModifierPizzaServiceTest {

	private List<Pizza> pizzas = new ArrayList<>();
	
	public ModifierPizzaServiceTest(){
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
	public void testExecuteUC() throws SavePizzaSQLException, UpdatePizzaSQLException{
		ModifierPizzaService mps = new ModifierPizzaService();
		systemInMock.provideLines("PEP", "PEPE", "Pépéroni", "12,5");
		PizzaMemDao dao = new PizzaMemDao();
		mps.executeUC(new Scanner(System.in), dao);
		
		List<Pizza> expected = pizzas;
		pizzas.get(0).setCode("PEPE");
		
		Assert.assertEquals(expected, dao.getPizzas());
	}
}
