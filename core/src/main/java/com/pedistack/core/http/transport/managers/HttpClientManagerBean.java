package com.pedistack.core.http.transport.managers;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClientManagerBean implements HttpClientManager {

  private final RestTemplate restTemplate;

  public HttpClientManagerBean(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public <T, U> ResponseEntity<U> send(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      T request)
      throws PedistackException {
    try {
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      final HttpEntity<T> httpEntity = new HttpEntity<>(request, httpHeaders);
      return restTemplate.exchange(
          url, httpMethod, httpEntity, new ParameterizedTypeReference<>() {});
    } catch (Exception exception) {
      throw PedistackException.communicationException(
          PedistackErrorDescriptions.HTTP_COMMUNICATION_ERROR_DESCRIPTION);
    }
  }

  @Override
  public <T> void publish(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      T request)
      throws PedistackException {
    try {
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);
      final HttpEntity<T> httpEntity = new HttpEntity<>(request, httpHeaders);
      restTemplate.exchange(url, httpMethod, httpEntity, new ParameterizedTypeReference<>() {});
    } catch (Exception exception) {
      throw PedistackException.communicationException(
          PedistackErrorDescriptions.HTTP_COMMUNICATION_ERROR_DESCRIPTION);
    }
  }

  @Override
  public <U> ResponseEntity<U> formData(
      String sessionUserIdentifier,
      String sessionReference,
      String url,
      HttpMethod httpMethod,
      HttpHeaders httpHeaders,
      MultiValueMap<String, String> formData)
      throws PedistackException {
    try {
      httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      final HttpEntity<MultiValueMap<String, String>> httpEntity =
          new HttpEntity<>(formData, httpHeaders);
      return restTemplate.exchange(
          url, httpMethod, httpEntity, new ParameterizedTypeReference<>() {});
    } catch (Exception exception) {
      throw PedistackException.communicationException(
          PedistackErrorDescriptions.HTTP_COMMUNICATION_ERROR_DESCRIPTION);
    }
  }
}
