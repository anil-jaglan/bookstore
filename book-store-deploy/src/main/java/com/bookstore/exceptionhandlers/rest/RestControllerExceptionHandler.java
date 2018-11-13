package com.bookstore.exceptionhandlers.rest;

import com.bookstore.common.errors.ErrorData;
import com.bookstore.common.exceptions.ExtendedRuntimeException;
import com.bookstore.config.GeneralConstants;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 
 * Generic Rest controllers exception handler advice.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@RestControllerAdvice(basePackages = GeneralConstants.APPLICATION_BASE_PACKAGE)
public class RestControllerExceptionHandler {

    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    /**
     * Instance properties for <code>RestControllerExceptionProperties</code>.
     */
    @Autowired
    private RestControllerExceptionProperties properties;

    /**
     * 
     * ExtendedRunTimeException handler for logging exception data and send only
     * error message in response.
     * 
     * @param ex
     *            - Extended runtime exception.
     * @param request
     *            - HttpServletRequest object.
     * @param response
     *            - HttpServletResponse object.
     * @return - Response data with status.
     */
    @ExceptionHandler(ExtendedRuntimeException.class)
    public final ResponseEntity<ErrorData> handleExtendedRunTimeExceptions(final ExtendedRuntimeException ex,
            final HttpServletRequest request, final HttpServletResponse response) {

        final String url = request.getRequestURL().toString();
        LOGGER.error("Exception in calling {}, Error: ", url, ex);

        return new ResponseEntity<>(ex.getErrorData(), HttpStatus.OK);
    }

    /**
     * 
     * Method to handle all exceptions.
     * 
     * @param ex
     *            - Exception object.
     * @param request
     *            - HttpServletRequest object.
     * @param response
     *            - HttpServletResponse object.
     * @return - Response data with status.
     */
    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> handleAllExceptions(final Throwable ex, final HttpServletRequest request,
            final HttpServletResponse response) {

        final String url = request.getRequestURL().toString();
        LOGGER.error("Exception in calling {}", url, ex);
        final String errorMessage = MessageFormat.format(
                properties.getMessage(RestControllerExceptionProperties.ERROR_MESSAGE_SYSTEM_TEXT),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 
     * Method to handle MethodArgumentTypeMismatch exceptions.
     * 
     * @param ex
     *            - Exception object.
     * @param request
     *            - HttpServletRequest object.
     * @param response
     *            - HttpServletResponse object.
     * @return - Response data with status.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
            final HttpServletRequest request, final HttpServletResponse response) {

        final String url = request.getRequestURL().toString();
        final String errorMessage = MessageFormat.format(
                properties.getMessage(RestControllerExceptionProperties.ERROR_MESSAGE_MISMATCH_ARGUMENT_TYPE),
                HttpStatus.BAD_REQUEST,
                ex.getName(),
                ex.getRequiredType().getName(),
                url);
        LOGGER.error(errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * This exception is thrown when argument annotated with @Valid failed
     * validation.
     *
     * @param ex
     *            The exception.
     * @param response
     *            The response.
     * @return The response entity.
     */
    @ExceptionHandler(
        { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> processValidationError(final MethodArgumentNotValidException ex,
            final HttpServletResponse response) {

        final BindingResult result = ex.getBindingResult();
        final Map<String, String> errorData = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errorData.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        LOGGER.error("{} : {}", HttpStatus.BAD_REQUEST, errorData);
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);

    }

}
