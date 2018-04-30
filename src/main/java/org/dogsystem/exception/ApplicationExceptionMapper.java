package org.dogsystem.exception;

import static org.dogsystem.exception.ExceptionConstants.PARAMETER_VALUE_EXCEPTION;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class ApplicationExceptionMapper extends ResponseEntityExceptionHandler {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionBean> handleControllerException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);

		String msg = exception.getMessage();

		if (exception instanceof NullPointerException) {
			msg = "Erro interno NPE";
		}

		ServerException exceptionBean = new ServerException(msg);

		return new ResponseEntity<>(new ExceptionBean(exceptionBean), exceptionBean.getHttpStatus());
	}

	@ResponseBody
	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<ExceptionBean> handleControllerException(SecurityException exception) {
		LOGGER.error(exception.getMessage(), exception);

		return new ResponseEntity<>(new ExceptionBean(exception), exception.getHttpStatus());
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionBean> handleControllerException(ConstraintViolationException exception) {

		ExceptionBean exceptionBean = new ExceptionBean();

		String msg = exception.getConstraintViolations().toString();

		exceptionBean.setMessage(msg.substring(msg.indexOf("=") + 1, msg.indexOf(", propertyPath=")));

		exceptionBean.setServerCode(PARAMETER_VALUE_EXCEPTION.getServerCode());

		return new ResponseEntity<>(exceptionBean, PARAMETER_VALUE_EXCEPTION.getHttpStatus());
	}

	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionBean> handleControllerException(DataIntegrityViolationException exception) {
		ExceptionBean exceptionBean = new ExceptionBean();

		StringBuffer buffer = new StringBuffer();
		if (exception.getRootCause().getLocalizedMessage().indexOf("Cannot delete or update") > -1) {
			buffer.append("Este registro já está ligado com outra tabela não sendo permitido sua exclusão");
		} else {
			buffer.append("\"" + exception.getRootCause().getLocalizedMessage().split("'")[1]);
			buffer.append("\" cadastrado no banco, favor verifique.");
		}

		exceptionBean.setMessage(buffer.toString());
		exceptionBean.setServerCode(PARAMETER_VALUE_EXCEPTION.getServerCode());

		return new ResponseEntity<>(exceptionBean, PARAMETER_VALUE_EXCEPTION.getHttpStatus());
	}

	@ResponseBody
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<ExceptionBean> handleControllerException(TransactionSystemException exception) {
		ExceptionBean exceptionBean = new ExceptionBean();
		exceptionBean.setMessage(exception.getRootCause().getLocalizedMessage().split("'")[1]);
		exceptionBean.setServerCode(PARAMETER_VALUE_EXCEPTION.getServerCode());

		return new ResponseEntity<>(exceptionBean, PARAMETER_VALUE_EXCEPTION.getHttpStatus());
	}
}