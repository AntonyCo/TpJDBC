package fr.pizzeria.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.pizzeria.exceptions.DeletePizzaSQLException;
import fr.pizzeria.exceptions.DriverMySQLException;
import fr.pizzeria.exceptions.PropertiesException;
import fr.pizzeria.exceptions.RetrievePizzaSQLException;
import fr.pizzeria.exceptions.SavePizzaSQLException;
import fr.pizzeria.exceptions.UpdatePizzaSQLException;
import fr.pizzeria.model.Pizza;

public class PizzaBaseDao implements IPizzaDao{
	private String driver;
	private String protocol;
	private String url;
	private String database;
	private String user;
	private String password;

	public PizzaBaseDao() throws PropertiesException, DriverMySQLException, FileNotFoundException{
		Properties prop = new Properties();
		FileInputStream input = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Load properties file and set the properties
			input = new FileInputStream("resources/jdbc.properties");
			prop.load(input);
			driver = prop.getProperty("driver");
			protocol = prop.getProperty("protocol");
			url = prop.getProperty("url");
			database = prop.getProperty("database");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			throw new PropertiesException();
		} catch (ClassNotFoundException e) {
			throw new DriverMySQLException();
		}
	}
	@Override
	public List<Pizza> findAllPizzas() throws RetrievePizzaSQLException {
		List<Pizza> pizzas = new ArrayList<>();
		
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			//Create prepared statement to select all pizzas
			PreparedStatement selectPizzas = myConnection.prepareStatement(""
					+ "SELECT * FROM pizza;");
			//Execute then browse the query
			ResultSet result = selectPizzas.executeQuery();
			while(result.next()){
				Integer id = result.getInt("ID");
				String code = result.getString("CODE");
				String wording = result.getString("WORDING");
				Double price = result.getDouble("PRICE");
				
				//Add pizza to List
				pizzas.add(new Pizza(id, code, wording, price));
			}
			//Close connection
			myConnection.close();
		} catch (SQLException e) {
			throw new RetrievePizzaSQLException();
		}
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaSQLException {
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			
			//Create prepared statement to insert a new pizza
			PreparedStatement savePizza = myConnection.prepareStatement(""
					+ "INSERT INTO pizza(CODE, WORDING, PRICE) "
					+ "VALUES(?, ?, ?)");
			//Set values to insert
			savePizza.setString(1, pizza.getCode());
			savePizza.setString(2, pizza.getLibelle());
			savePizza.setDouble(3, pizza.getPrix());
			//Execute the query then close connection
			savePizza.executeUpdate();
			myConnection.close();
		} catch (SQLException e) {
			throw new SavePizzaSQLException();
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaSQLException {
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			
			//Create prepared statement to update an existing pizza
			PreparedStatement savePizza = myConnection.prepareStatement(""
					+ "UPDATE pizza "
					+ "SET CODE=?, WORDING=?, PRICE=? "
					+ "WHERE CODE = ?;");
			//Set values to update
			savePizza.setString(1, pizza.getCode());
			savePizza.setString(2, pizza.getLibelle());
			savePizza.setDouble(3, pizza.getPrix());
			//Where the code pizza is codePizza
			savePizza.setString(4, codePizza);
			//Execute the query then close connection
			savePizza.executeUpdate();
			myConnection.close();
		} catch (SQLException e) {
			throw new UpdatePizzaSQLException();
		}
		
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaSQLException {
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			
			//Create prepared statement to delete an existing pizza
			PreparedStatement deletePizza = myConnection.prepareStatement(""
					+ "DELETE FROM pizza WHERE CODE = ?;");
			//Where the code pizza is codePizza
			deletePizza.setString(1, codePizza);
			//Execute the query then close connection
			deletePizza.executeUpdate();
			myConnection.close();
		} catch (SQLException e) {
			throw new DeletePizzaSQLException();
		}
		
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws RetrievePizzaSQLException {
		Pizza pizza = null;
		
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			
			//Create prepared statement to select the pizza
			PreparedStatement selectPizza = myConnection.prepareStatement(""
					+ "SELECT * FROM pizza WHERE CODE = ?;");
			
			selectPizza.setString(1, codePizza);
			//Execute then browse the query
			ResultSet result = selectPizza.executeQuery();
			while(result.next()){
				Integer id = result.getInt("ID");
				String code = result.getString("CODE");
				String wording = result.getString("WORDING");
				Double price = result.getDouble("PRICE");
				
				pizza = new Pizza(id, code, wording, price); 	
			}
			//Close connection
			myConnection.close();
		} catch (SQLException e) {
			throw new RetrievePizzaSQLException("Unable to retrieve pizza with entered code from database !");
		}
		
		return pizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) throws RetrievePizzaSQLException {
		boolean isExist = false;
		try {
			//Open the connection to database
			Connection myConnection = DriverManager.getConnection(driver+":"+protocol+":"+url+database, user, password);
			
			//Create prepared statement to select the pizza
			PreparedStatement selectPizza = myConnection.prepareStatement(""
					+ "SELECT * FROM pizza WHERE CODE = ?;");
			
			selectPizza.setString(1, codePizza);
			//Execute the query, if the query return true, the pizza exist 
			ResultSet result = selectPizza.executeQuery();
			if(result.next()){
				isExist = true;
			}
			//Close connection
			myConnection.close();
		} catch (SQLException e) {
			throw new RetrievePizzaSQLException("Unable to check if pizza exist with entered code from database !");
		}
		return isExist;
	}
	
	@Override
	public void initBaseFromArray(List<Pizza> pizzas) throws SavePizzaSQLException {
		for(Pizza p : pizzas){
			this.saveNewPizza(p);
		}
	}

}
