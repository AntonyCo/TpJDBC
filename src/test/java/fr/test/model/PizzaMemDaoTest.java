package fr.test.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {
	
	private List<Pizza> pizzas = new ArrayList<>();
	
	public PizzaMemDaoTest(){
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
	}
	
	@Test
	public void testDeletePizza(){
		PizzaMemDao pmd = new PizzaMemDao();
		pmd.deletePizza("REIN");
		
		List<Pizza> expected = pizzas;
		expected.remove(2);
		assertEquals(expected, pmd.getPizzas());
	}
	
	@Test
	public void testFindAllPizzas(){
		PizzaMemDao pmd = new PizzaMemDao();
		
		assertEquals(pizzas, pmd.findAllPizzas());
	}
	
	@Test
	public void testSaveNewPizza(){
		PizzaMemDao pmd = new PizzaMemDao();
		Pizza p = new Pizza("TEST", "Testing", 5d);
		List<Pizza> expected = pizzas;
		expected.add(p);
		
		pmd.saveNewPizza(p);
		
		assertEquals(expected, pmd.getPizzas());
	}
	
	@Test
	public void testUpdatePizza(){
		PizzaMemDao pmd = new PizzaMemDao();
		List<Pizza> expected = pizzas;
		expected.get(0).setCode("PEPE");
		
		pmd.updatePizza("PEP", new Pizza("PEPE", "Pépéroni", 12.50));
		
		assertEquals(expected, pmd.getPizzas());
	}
}
