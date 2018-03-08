package org.dogsystem.exception;

import static org.dogsystem.exception.ExceptionConstants.SECURITY_EXCEPTION;

public class SecurityException extends GenericException {

	private static final long serialVersionUID = 201606062325L;

	public SecurityException() {
		super(SECURITY_EXCEPTION);
	}

}
