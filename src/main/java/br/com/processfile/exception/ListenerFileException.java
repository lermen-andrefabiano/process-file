package br.com.processfile.exception;

public class ListenerFileException extends Exception {

	private static final long serialVersionUID = -5384313310093414728L;

	public ListenerFileException(String errorMessage) {
		super(errorMessage);
	}

	public ListenerFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
