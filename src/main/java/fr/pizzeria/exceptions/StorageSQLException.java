package fr.pizzeria.exceptions;

import java.sql.SQLException;

public class StorageSQLException extends SQLException{
	public StorageSQLException(){
		
	}
	public StorageSQLException(String msg){
		super(msg);
	}
}
