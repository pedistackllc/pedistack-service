package com.pedistack.common.db;

import java.util.List;

import com.pedistack.common.exception.PedistackException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseDaoManager<U extends BaseEntity, T extends BaseDao<U>> {

  U save(U entity) throws PedistackException;

  List<U> saveAll(List<U> entity);

  void delete(U entity) throws PedistackException;

  U findByIdentifier(String identifier) throws PedistackException;

  Page<U> findAll(Pageable pageable) throws PedistackException;

}
