package fr.pizzeria.exceptions;

public class DriverMySQLException extends Exception{
	public DriverMySQLException(){
		super("Unable to load the driver MySQL !");
	}
	public DriverMySQLException(String msg){
		super(msg);
	}
}
