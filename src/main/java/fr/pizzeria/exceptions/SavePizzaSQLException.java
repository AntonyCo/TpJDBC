package fr.pizzeria.exceptions;

public class SavePizzaSQLException extends StorageSQLException{
	public SavePizzaSQLException(){
		super("Unable to save Pizza to database !");
	}
	
	public SavePizzaSQLException(String msg){
		super(msg);
	}
}
