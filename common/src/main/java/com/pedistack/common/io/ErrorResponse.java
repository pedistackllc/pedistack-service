package com.pedistack.common.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.common.exception.PedistackException;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ErrorResponse implements Serializable {

  private final String errorCode;
  private final String errorName;
  private final String errorDescription;

  public ErrorResponse() {
    this.errorCode = null;
    this.errorDescription = null;
    this.errorName = null;
  }

  private ErrorResponse(String errorCode, String errorName, String errorDescription) {
    this.errorCode = errorCode;
    this.errorName = errorName;
    this.errorDescription = errorDescription;
  }

  public static ErrorResponse create(final PedistackException pedistackException) {
    return new ErrorResponse(
        pedistackException.getErrorCode().name(),
        pedistackException.getBillErrorDescription().getDescription(),
        pedistackException.getBillErrorDescription().getDetailedDescription());
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public String getErrorName() {
    return errorName;
  }
}
