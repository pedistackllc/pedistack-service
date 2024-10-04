package com.pedistack.core.http.transport.managers;

import com.pedistack.common.exception.PedistackException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface HttpClientManager {

  <T, U> ResponseEntity<U> send(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      T request)
      throws PedistackException;

  <T> void publish(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      T request)
      throws PedistackException;

  <U> ResponseEntity<U> formData(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      MultiValueMap<String, String> formData)
      throws PedistackException;
}
