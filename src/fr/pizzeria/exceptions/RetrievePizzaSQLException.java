package fr.pizzeria.exceptions;

public class RetrievePizzaSQLException extends StorageSQLException{
	public RetrievePizzaSQLException(){
		super("Problem to retrieve pizza from database !");
	}
	public RetrievePizzaSQLException(String msg){
		super(msg);
	}
}
