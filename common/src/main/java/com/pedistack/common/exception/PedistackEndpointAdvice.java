package com.pedistack.common.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.pedistack.common.io.ErrorResponse;
import com.pedistack.common.io.GenericResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class PedistackEndpointAdvice {

  @ExceptionHandler(
      value = {DataIntegrityViolationException.class, ConstraintViolationException.class})
  public ResponseEntity<GenericResponse<ErrorResponse>> createDataIntegrityErrorResponse(
      final DataIntegrityViolationException dataIntegrityViolationException) {
    LoggerFactory.getLogger(PedistackEndpointAdvice.class)
        .error(dataIntegrityViolationException.getMessage(), dataIntegrityViolationException);
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            GenericResponse.createResponse(
                ErrorResponse.create(
                    PedistackException.createInternalErrorException(
                        PedistackErrorDescription.createDescription(
                            "An error has occurred. Please contact customer support",
                            "Unable to complete this operation"))),
                "An error occurred while completing this operation"));
  }

  @ExceptionHandler(value = {NoClassDefFoundError.class, NullPointerException.class})
  public ResponseEntity<GenericResponse<ErrorResponse>> generalErrorResponse(
      final NoClassDefFoundError noClassDefFoundError) {
    LoggerFactory.getLogger(PedistackEndpointAdvice.class)
        .error(noClassDefFoundError.getMessage(), noClassDefFoundError);
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            GenericResponse.createResponse(
                ErrorResponse.create(
                    PedistackException.createInternalErrorException(
                        PedistackErrorDescription.createDescription(
                            "An error has occurred. Please contact customer support",
                            "Unable to complete this operation"))),
                "An error occurred while completing this operation"));
  }

  @ExceptionHandler(value = {RuntimeException.class, Exception.class})
  public ResponseEntity<GenericResponse<ErrorResponse>> runtimeException(
      final Exception exception) {
    LoggerFactory.getLogger(PedistackEndpointAdvice.class).error(exception.getMessage(), exception);
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            GenericResponse.createResponse(
                ErrorResponse.create(
                    PedistackException.createInternalErrorException(
                        PedistackErrorDescription.createDescription(
                            "An error has occurred. Please contact customer support",
                            "Unable to complete this operation"))),
                exception.getMessage()));
  }

  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<GenericResponse<ErrorResponse>> illegalArgumentException(
      final IllegalArgumentException illegalArgumentException) {
    LoggerFactory.getLogger(PedistackEndpointAdvice.class)
        .error(illegalArgumentException.getMessage(), illegalArgumentException);
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            GenericResponse.createResponse(
                ErrorResponse.create(
                    PedistackException.createInternalErrorException(
                        PedistackErrorDescription.createDescription(
                            "An error has occurred. Please contact customer support",
                            "Unable to complete this operation"))),
                illegalArgumentException.getMessage()));
  }

  @ExceptionHandler(value = {PedistackException.class})
  public ResponseEntity<GenericResponse<ErrorResponse>> createErrorResponse(
      PedistackException pedistackException) {
    LoggerFactory.getLogger(PedistackEndpointAdvice.class)
        .error(pedistackException.getErrorCode().name(), pedistackException);
    return switch (pedistackException.getErrorCode()) {
      case INTERNAL_ERROR ->
          ResponseEntity.status(INTERNAL_SERVER_ERROR)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "Internal error occurred"));
      case BAD_REQUEST ->
          ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "Bad request found"));
      case UNAUTHORIZED ->
          ResponseEntity.status(HttpStatus.UNAUTHORIZED)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "Unauthorized access"));
      case COMMUNICATION_ERROR ->
          ResponseEntity.status(HttpStatus.BAD_GATEWAY)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "Communication error found"));
      case ACCESS_EXPIRED ->
          ResponseEntity.status(HttpStatus.IM_USED)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "Expired access to the resource"));
      case NOT_FOUND ->
          ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(
                  GenericResponse.createResponse(
                      ErrorResponse.create(pedistackException), "The resource is not available"));
    };
  }
}
