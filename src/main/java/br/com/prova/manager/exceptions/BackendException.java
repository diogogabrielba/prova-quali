package br.com.prova.manager.exceptions;

public final class BackendException extends Exception{

	private static final long serialVersionUID = -1600459825743063296L;

	public BackendException( String message ){
		super( message );

	}

	public BackendException( String message, Throwable cause ){
		super( message, cause );

	}

}
