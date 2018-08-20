package fr.pizzeria.exceptions;

public class DeletePizzaSQLException extends StorageSQLException{
	  public DeletePizzaSQLException() {
		  super("Unable to delete Pizza from database !");
	  } 
	  
	  public DeletePizzaSQLException(String msg) { 
		  super(msg); 
	  }
}
