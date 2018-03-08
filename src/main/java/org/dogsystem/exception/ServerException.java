package org.dogsystem.exception;

import static org.dogsystem.exception.ExceptionConstants.SERVER_EXCEPTION;

public class ServerException extends GenericException {

	private static final long serialVersionUID = 1L;

	public ServerException(String error) {
		super(SERVER_EXCEPTION);
		getExceptionBean().setMessage(String.format(SERVER_EXCEPTION.getMessage(), error));
	}
}
