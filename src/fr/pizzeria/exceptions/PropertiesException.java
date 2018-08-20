package fr.pizzeria.exceptions;

public class PropertiesException extends Exception{
	public PropertiesException(){
		super("Unable to load the properties file !");
	}

	public PropertiesException(String msg){
		super(msg);
	}
}
