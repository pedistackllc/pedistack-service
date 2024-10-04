package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.FinancialAccountProviderRequest;
import com.pedistack.accounts.v1_0.common.FinancialAccountProvider;
import com.pedistack.accounts.v1_0.common.FinancialAccountProviderType;
import com.pedistack.common.managers.CrudOperationManager;
import java.util.List;

public interface FinancialAccountProviderOperationManager
    extends CrudOperationManager<FinancialAccountProviderRequest, FinancialAccountProvider> {

  List<FinancialAccountProvider> listByFinancialAccountProviderType(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      FinancialAccountProviderType financialAccountProviderType,String countryCode);

  List<FinancialAccountProvider> listByFinancialAccountProviderType(
          String tenant,
          String sessionUserIdentifier,
          String sessionReference,
          String countryCode);
}
