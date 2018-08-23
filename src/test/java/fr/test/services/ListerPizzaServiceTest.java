package fr.test.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.RetrievePizzaSQLException;
import fr.pizzeria.exceptions.SavePizzaSQLException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.ListerPizzasService;

public class ListerPizzaServiceTest {
	private List<Pizza> pizzas = new ArrayList<>();
	
	public ListerPizzaServiceTest(){
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
	
	@Rule
	public SystemOutRule sysOutMock = new SystemOutRule();
	@Test
	public void testExecuteUC() throws SavePizzaSQLException, RetrievePizzaSQLException{
		ListerPizzasService lps = new ListerPizzasService();
		PizzaMemDao dao = new PizzaMemDao();
		sysOutMock.enableLog();
		lps.executeUC(null, dao);
		String returnValue = sysOutMock.getLog().trim();
		System.out.println("log: "+returnValue.length());
		
		boolean test =
		returnValue.contains("Lister les pizzas") &
		returnValue.contains("PEP->Pépéroni (12.5 €)") &
		returnValue.contains("MAR->Margherita (14.0 €)") &
		returnValue.contains("REIN->La Reine (11.5 €)") &
		returnValue.contains("FRO->La 4 fromages (12.0 €)") &
		returnValue.contains("CAN->La cannibale (12.5 €)") &
		returnValue.contains("SAV->La savoyarde (13.0 €)") &
		returnValue.contains("ORI->L’orientale (13.5 €)") &
		returnValue.contains("IND->L’indienne (14.0 €)");
		
		Assert.assertEquals(true, test);
	}
}
