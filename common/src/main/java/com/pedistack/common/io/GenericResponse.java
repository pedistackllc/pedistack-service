package com.pedistack.common.io;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.utils.Jsons;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GenericResponse<T> implements Serializable {

  private final T responseBody;
  private final String responseDescription;
  private final Date responseTimestamp;

  private GenericResponse() {
    this.responseBody = null;
    this.responseTimestamp = null;
    this.responseDescription = null;
  }

  private GenericResponse(T responseBody, String responseDescription) {
    this.responseBody = responseBody;
    this.responseDescription = responseDescription;
    this.responseTimestamp = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
  }

  public static <T> GenericResponse<T> createResponse(T responseBody, String responseDescription) {
    return new GenericResponse<>(responseBody, responseDescription);
  }

  public static <T> GenericResponse<T> createResponse(String responseDescription) {
    return new GenericResponse<>(null, responseDescription);
  }

  public Date getResponseTimestamp() {
    return responseTimestamp;
  }

  public String getResponseDescription() {
    return responseDescription;
  }

  public T getResponseBody() {
    return responseBody;
  }

  @Override
  public String toString() {
    try {
      return Jsons.toJsonString(this);
    } catch (PedistackException e) {
      throw new RuntimeException(e);
    }
  }
}
