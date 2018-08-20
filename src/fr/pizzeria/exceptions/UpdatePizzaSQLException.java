package fr.pizzeria.exceptions;

public class UpdatePizzaSQLException extends StorageSQLException{
	public UpdatePizzaSQLException(){
		super("Unable to update Pizza from database !");
	}
	
	public UpdatePizzaSQLException(String msg){
		super(msg);
	}
}
