package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.StorageSQLException;

public abstract class MenuService {

	public abstract void executeUC(Scanner scanner, IPizzaDao dao) throws StorageSQLException;
}
