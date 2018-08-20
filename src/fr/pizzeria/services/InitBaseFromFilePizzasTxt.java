package fr.pizzeria.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.StorageSQLException;
import fr.pizzeria.model.Pizza;

public class InitBaseFromFilePizzasTxt extends MenuService{

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws StorageSQLException {
		List<Pizza> pizzas = new ArrayList<>();
		//Read the file
		try {
			File f = new File ("resources/pizzas.txt");
		    FileReader fr = new FileReader (f);
		    BufferedReader br = new BufferedReader (fr);
			String line = br.readLine();
			   
			while (line != null){
				 String[] array = line.split(",");
				 //Add a pizza from file to list
				 pizzas.add(new Pizza(array[0], array[1], Double.parseDouble(array[2])));
				line = br.readLine();
			}
			 
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.initBaseFromArray(pizzas);
	}

}
