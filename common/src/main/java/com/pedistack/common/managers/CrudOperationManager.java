package com.pedistack.common.managers;


import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;

public interface CrudOperationManager<Rq, Rs> {

  Rs create(String tenant,String sessionUserIdentifier, String sessionReference, Rq request)
      throws PedistackException;

  Rs get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException;

  PageResponse<Rs> list(String tenant,String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException;

  Rs update(String tenant,String sessionUserIdentifier, String sessionReference, String identifier, Rq request)
      throws PedistackException;

  void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException;
}
