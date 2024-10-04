package com.pedistack.admins.operation.managers;

import com.pedistack.admins.v1_0.AdminUserRequest;
import com.pedistack.admins.v1_0.common.AdminUser;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.CrudOperationManager;

public interface AdminUserOperationManager
    extends CrudOperationManager<AdminUserRequest, AdminUser> {

  void activateAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException;

  void blockAdminUser(String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException;

  void unblockAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException;

  void closeAdminUser(String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException;
}
