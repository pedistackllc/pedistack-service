package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface UserEntityDaoManager extends BaseDaoManager<UserEntity, UserEntityDao> {

  Optional<UserEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException;

  UserEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  UserEntity findByEmailAddress(String emailAddress) throws PedistackException;

  Optional<UserEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException;

  UserEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  UserEntity findByMobileNumber(String mobileNumber) throws PedistackException;

  Optional<UserEntity> findByUsernameReturnOptional(String username) throws PedistackException;

  UserEntity findByUsername(String username, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  UserEntity findByUsername(String username) throws PedistackException;

  Optional<UserEntity> findByClientIdReturnOptional(String clientId) throws PedistackException;

  UserEntity findByClientId(String clientId, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  UserEntity findByClientId(String clientId) throws PedistackException;

  void checkExistingUsername(String username) throws PedistackException;

  void checkExistingEmailAddress(String emailAddress) throws PedistackException;

  void checkExistingMobileNumber(String mobileNumber) throws PedistackException;
}
