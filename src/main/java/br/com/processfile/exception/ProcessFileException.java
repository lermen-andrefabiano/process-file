package br.com.processfile.exception;

public class ProcessFileException extends Exception {

	private static final long serialVersionUID = 8479637832243016345L;

	public ProcessFileException(String errorMessage) {
		super(errorMessage);
	}

	public ProcessFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
