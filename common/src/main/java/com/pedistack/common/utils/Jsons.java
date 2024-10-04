package com.pedistack.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;

public final class Jsons {

  public static <T> String toJsonString(T object) throws PedistackException {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException jsonProcessingException) {
      throw PedistackException.createBadRequestException(
          PedistackErrorDescriptions.DATA_ERROR_DESCRIPTION);
    }
  }

  public static <T> T fromJsonString(final String jsonString, Class<T> tClass)
      throws PedistackException {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(jsonString, tClass);
    } catch (JsonProcessingException jsonProcessingException) {
      throw PedistackException.createBadRequestException(
          PedistackErrorDescriptions.DATA_ERROR_DESCRIPTION);
    }
  }

  public static <T> List<T> fromListJsonString(final String jsonString, Class<T> tClass)
      throws PedistackException {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(
          jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
    } catch (JsonProcessingException jsonProcessingException) {
      throw PedistackException.createBadRequestException(
          PedistackErrorDescriptions.DATA_ERROR_DESCRIPTION);
    }
  }
}
