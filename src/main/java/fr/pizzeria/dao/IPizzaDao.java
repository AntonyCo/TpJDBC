package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DeletePizzaSQLException;
import fr.pizzeria.exceptions.RetrievePizzaSQLException;
import fr.pizzeria.exceptions.SavePizzaSQLException;
import fr.pizzeria.exceptions.UpdatePizzaSQLException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	List<Pizza> findAllPizzas() throws RetrievePizzaSQLException;
	void saveNewPizza(Pizza pizza) throws SavePizzaSQLException;
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaSQLException;
	void deletePizza(String codePizza) throws DeletePizzaSQLException;
	void initBaseFromArray(List<Pizza> pizzas) throws SavePizzaSQLException;
	Pizza findPizzaByCode(String codePizza) throws RetrievePizzaSQLException;
	boolean pizzaExists(String codePizza) throws RetrievePizzaSQLException;
}

