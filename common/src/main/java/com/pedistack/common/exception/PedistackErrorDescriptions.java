package com.pedistack.common.exception;

public final class PedistackErrorDescriptions {

  public static PedistackErrorDescription DATA_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Data parsing error", "Unable to parse data. Please contact administrator");

  public static PedistackErrorDescription ACCOUNT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Account already registered. Please try again",
          "This account is already registered. Please contact administrator");

  public static PedistackErrorDescription GENERAL_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "A general error occurred. Please try again",
          "Generic error. Please contact administrator");

  public static PedistackErrorDescription IDENTITY_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Invalid identity. The identity requested is not registered",
          "We are unable to validate and find your identity. Please contact administrator");

  public static PedistackErrorDescription AUTHORIZATION_TYPE_NOT_SUPPORTED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Authorization header not supported",
          "Authorization header is not supported. Please try again");

  public static PedistackErrorDescription EXPIRED_ACCESS_TOKEN_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Your access token is expired. Please request another access token",
          "Access token is expired. Please try again");

  public static PedistackErrorDescription PROFILE_ALREADY_REGISTERED =
      PedistackErrorDescription.createDescription(
          "Profile already registered. Please try again",
          "The profile requested is already registered. Please try again");

  public static PedistackErrorDescription PROFILE_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Profile not registered. Please try again",
          "The profile requested is not registered. Please try again");

  public static PedistackErrorDescription PROFILE_TYPE_INVALID_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Invalid profile type for this operation",
          "The profile type required for this operation is not available. Please try again");

  public static PedistackErrorDescription EMAIL_ADDRESS_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Email address not found. Please try again",
          "The email address requested is not registered. Please try again");

  public static PedistackErrorDescription MSISDN_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Mobile number not found. Please try again",
          "The mobile number requested is not registered. Please try again");

  public static PedistackErrorDescription USERNAME_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Username not found. Please try again",
          "The username requested is not registered. Please try again");

  public static PedistackErrorDescription USER_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "User not found. Please try again",
          "The user requested is not registered. Please try again");

  public static PedistackErrorDescription USER_NOT_ACTIVE =
      PedistackErrorDescription.createDescription(
          "User not active. Please try again",
          "The user requested is not active. Please try again");

  public static PedistackErrorDescription AUTHORIZATION_ERROR =
      PedistackErrorDescription.createDescription(
          "Unable to authorize this user. Please try again",
          "An error occurred while authorizing the user. Please try again");

  public static PedistackErrorDescription CREDENTIAL_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to validate this credential. Please try again",
          "An error occurred while validating the credential. Please try again");

  public static PedistackErrorDescription AUTHORIZATION_HEADER_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Authorization header not available. Please try again",
          "The authentication information cannot be found. Please try again");

  public static PedistackErrorDescription USER_WITH_MOBILE_NUMBER_REGISTERED =
      PedistackErrorDescription.createDescription(
          "Mobile number already registered. Please try again",
          "The user with mobile number is already registered. Please try again");

  public static PedistackErrorDescription USER_WITH_USERNAME_REGISTERED =
      PedistackErrorDescription.createDescription(
          "Username already registered. Please try again",
          "The user with username is already registered. Please try again");

  public static PedistackErrorDescription USER_WITH_EMAIL_ADDRESS_REGISTERED =
      PedistackErrorDescription.createDescription(
          "Email address already registered. Please try again",
          "The user with email address is already registered. Please try again");

  public static PedistackErrorDescription USER_ALREADY_ACTIVATED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "User already activated. Please try again",
          "The user with identity is already activated. Please try again");

  public static PedistackErrorDescription IDENTITY_REGISTRATION_ERROR =
      PedistackErrorDescription.createDescription(
          "Registration error. Please contact administrator",
          "An error occurred while registering the identity. Please try again");

  public static PedistackErrorDescription MERCHANT_REGISTRATION_ERROR =
      PedistackErrorDescription.createDescription(
          "Merchant registration error. Please contact administrator",
          "An error occurred while registering the merchant. Please try again");

  public static PedistackErrorDescription ACTIVATION_ERROR =
      PedistackErrorDescription.createDescription(
          "Activation error", "An error occurred while activating the account. Please try again");

  public static PedistackErrorDescription EXPIRED_ACTIVATION_OTP =
      PedistackErrorDescription.createDescription(
          "Expired activation OTP. Kindly request a new OTP",
          "The OTP sent is not active. Please request a new OTP");

  public static PedistackErrorDescription EXPIRED_CREDENTIAL =
      PedistackErrorDescription.createDescription(
          "Expired credential. Please check your credential",
          "The credential is expired. Please check your credential");

  public static PedistackErrorDescription ACCOUNT_OWNER_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Account owner not found. Please try again",
          "The account owner requested is not registered. Please try again");

  public static PedistackErrorDescription IDENTIFICATION_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Identification not found. Please try again",
          "The identification requested is not registered. Please try again");

  public static PedistackErrorDescription BUSINESS_INFORMATION_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Business information not found. Please try again",
          "The business information requested is not registered. Please try again");

  public static PedistackErrorDescription BUSINESS_OWNER_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Business owner not found. Please try again",
          "The business owner requested is not registered. Please try again");

  public static PedistackErrorDescription DOCUMENT_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Document not found. Please try again",
          "The document requested is not registered. Please try again");

  public static PedistackErrorDescription CATEGORY_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Category already registered", "The category is already registered. Please try again");

  public static PedistackErrorDescription CATEGORY_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Category not found", "The category is not found. Please try again");

  public static PedistackErrorDescription CATEGORY_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing bill categories. Please try again",
          "Error importing bill categories. Please try again");

  public static PedistackErrorDescription AGGREGATOR_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing bill aggregators. Please try again",
          "Error importing bill aggregators. Please try again");

  public static PedistackErrorDescription PROFILE_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing user profiles. Please try again",
          "Error importing user profiles. Please try again");

  public static PedistackErrorDescription BILL_PROVIDER_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing bill providers. Please try again",
          "Error importing bill providers. Please try again");

  public static PedistackErrorDescription BILL_AGGREGATOR_PRODUCTS_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing bill aggregator products. Please try again",
          "Error importing bill aggregator products. Please try again");

  public static PedistackErrorDescription BILL_AGGREGATOR_PROVIDER_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing bill aggregator providers. Please try again",
          "Error importing bill aggregator providers. Please try again");

  public static PedistackErrorDescription PROVIDER_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Provider already registered", "The provider already registered. Please try again");

  public static PedistackErrorDescription PROVIDER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Provider not found", "The provider not registered. Please try again");

  public static PedistackErrorDescription COUNTRY_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Country already registered", "The country already registered. Please try again");

  public static PedistackErrorDescription CURRENCY_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Currency not found", "The currency not available. Please try again");

  public static PedistackErrorDescription CURRENCY_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Currency already registered", "The currency already registered. Please try again");

  public static PedistackErrorDescription COUNTRY_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Country not registered", "The country is not registered. Please try again");

  public static PedistackErrorDescription PRODUCT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Product already registered", "The product is already registered. Please try again");

  public static PedistackErrorDescription PRODUCT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Product not found", "The product is not available. Please try again");

  public static PedistackErrorDescription PRODUCT_FIELD_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Product field not found", "The product field not found. Please try again");

  public static PedistackErrorDescription PRODUCT_FIELD_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Product field already registered",
          "The product field already registered. Please try again");

  public static PedistackErrorDescription SOURCE_ACCOUNT_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Source account not available",
          "The source account cannot be found for this transaction");

  public static PedistackErrorDescription DESTINATION_ACCOUNT_NOT_FOUND =
      PedistackErrorDescription.createDescription(
          "Destination account not available",
          "The destination account cannot be found for this transaction");

  public static PedistackErrorDescription CURRENCY_MISMATCH_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Currency mismatch. The currencies in the account does not match",
          "The source and destination account currency does not match");

  public static PedistackErrorDescription ACCOUNT_ACCESS_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The access to this account is not allowed. Only the owner of the account can access the account",
          "The source account owner is not the same as the session user. Please make sure you have the right access");

  public static PedistackErrorDescription SOURCE_ACCOUNT_OWNER_ACTIVE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The owner of the source account is not active. Please make sure your user account is active",
          "The source user account is not active. Please contact the administrator to complete transaction");

  public static PedistackErrorDescription DESTINATION_ACCOUNT_OWNER_ACTIVE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The owner of the destination account is not active. Please make sure your user account is active",
          "The destination user account is not active. Please contact the administrator to complete transaction");

  public static PedistackErrorDescription SOURCE_ACCOUNT_ACTIVE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The source account is not active. Please try again",
          "The source account is not active at the moment. Please contact the administrator to complete transaction");

  public static PedistackErrorDescription DESTINATION_ACCOUNT_ACTIVE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The destination account is not active. Please try again",
          "The destination account is not active at the moment. Please contact the administrator to complete transaction");

  public static PedistackErrorDescription INSUFFICIENT_FUNDS_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Insufficient account balance. Please fund your account",
          "The source account does not have enough balance to complete this transaction. Please fund your account.");

  public static PedistackErrorDescription PROVIDER_ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to find the bill provider account. Please contact administrator",
          "The bill provider account required for this operation is not registered");

  public static PedistackErrorDescription BILL_AGGREGATOR_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to find the bill aggregator. Please contact administrator",
          "The bill aggregator is not registered. Please contact the administrator");

  public static PedistackErrorDescription BILL_AGGREGATOR_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The aggregator is already registered. Please contact administrator",
          "The bill aggregator is already registered. Please contact the administrator");

  public static PedistackErrorDescription AGGREGATOR_NOT_AVAILABLE_IN_COUNTRY_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "There is no registered provider for this country",
          "We do not have any registered aggregator in this country. Please try again");

  public static PedistackErrorDescription UNABLE_TO_VERIFY_BILL_ACCOUNT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to verify bill account", "The bill account cannot be verified. Please try again");

  public static PedistackErrorDescription UNABLE_TO_COMPLETE_BILL_PAYMENT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to complete bill payment",
          "The bill payment cannot be completed. Please try again");

  public static PedistackErrorDescription DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Developer information not found.", "The developer information not registered.");

  public static PedistackErrorDescription CURRENCY_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing currencies. Please try again",
          "Error importing currencies. Please try again");

  public static PedistackErrorDescription COUNTRY_IMPORT_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "An error occurred while importing countries. Please try again",
          "Error importing countries. Please try again");

  public static PedistackErrorDescription BILL_PAYMENT_SIZE_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The size of bill payment limit exceeded. The maximum allowed is 10 bill products.",
          "Error processing payments. Please try again");

  public static PedistackErrorDescription FX_EXCHANGE_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to find information about this FX exchange.",
          "Error finding the FX exchange information. Please try again");

  public static PedistackErrorDescription FX_EXCHANGE_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "FX exchange already registered. Please try again.",
          "Error registering the FX exchange information. Please try again");

  public static PedistackErrorDescription FX_EXCHANGE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to complete the FX exchange for this transaction.",
          "Error completing the FX exchange information. Please try again");

  public static PedistackErrorDescription HOURLY_TRANSACTION_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your hourly transaction amount limit. Please try again later",
          "The hourly transaction limit has been exceeded. Please try again");

  public static PedistackErrorDescription HOURLY_TRANSACTION_COUNT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your hourly transaction count limit. Please try again later",
          "The hourly transaction count has been exceeded. Please try again");

  public static PedistackErrorDescription DAILY_TRANSACTION_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your daily transaction amount limit. Please try again later",
          "The daily transaction limit has been exceeded. Please try again");

  public static PedistackErrorDescription DAILY_TRANSACTION_COUNT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your daily transaction count limit. Please try again later",
          "The daily transaction count has been exceeded. Please try again");

  public static PedistackErrorDescription WEEKLY_TRANSACTION_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your weekly transaction amount limit. Please try again later",
          "The weekly transaction limit has been exceeded. Please try again");

  public static PedistackErrorDescription WEEKLY_TRANSACTION_COUNT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your weekly transaction count limit. Please try again later",
          "The weekly transaction count has been exceeded. Please try again");

  public static PedistackErrorDescription MONTHLY_TRANSACTION_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your monthly transaction amount limit. Please try again later",
          "The monthly transaction limit has been exceeded. Please try again");

  public static PedistackErrorDescription MONTHLY_TRANSACTION_COUNT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your monthly transaction count limit. Please try again later",
          "The monthly transaction count has been exceeded. Please try again");

  public static PedistackErrorDescription ANNUAL_TRANSACTION_LIMIT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your annual transaction amount limit. Please try again later",
          "The annual transaction limit has been exceeded. Please try again");

  public static PedistackErrorDescription ANNUAL_TRANSACTION_COUNT_EXCEEDED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "You have exceeded your annual transaction count limit. Please try again later",
          "The annual transaction count has been exceeded. Please try again");

  public static PedistackErrorDescription
      BILL_AGGREGATOR_PRODUCT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "The bill aggregator product already registered. Please try again later",
              "The bill aggregator product already registered. Please try again");

  public static PedistackErrorDescription BILL_AGGREGATOR_PRODUCT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The bill aggregator product not found. Please try again later",
          "The bill aggregator product not found. Please try again");

  public static PedistackErrorDescription BILL_AGGREGATOR_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The bill aggregator provider not found. Please try again later",
          "The bill aggregator provider not found. Please try again");

  public static PedistackErrorDescription
      BILL_AGGREGATOR_PROVIDER_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "The bill aggregator provider not found. Please try again later",
              "The bill aggregator provider not found. Please try again");

  public static PedistackErrorDescription INVALID_SCHEDULED_START_DATE_TIME =
      PedistackErrorDescription.createDescription(
          "Scheduled start date and time must be after the current date",
          "Invalid start date and time. Please try again");

  public static PedistackErrorDescription INVALID_NUMBER_OF_OCCURRENCES =
      PedistackErrorDescription.createDescription(
          "Invalid number of occurrences.", "Invalid number of occurrences. Please try again");

  public static PedistackErrorDescription SCHEDULED_BILL_TRANSACTION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Scheduled bill transaction not found. Please use the right reference",
          "Unable to find the scheduled transaction. Please try again");

  public static PedistackErrorDescription SCHEDULED_BILL_PAYMENT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Scheduled bill payment not found. Please use the right reference",
          "Unable to find the scheduled payment. Please try again");

  public static PedistackErrorDescription BILL_PAYMENT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Bill payment not found. Please use the right reference",
          "Unable to find the payment. Please try again");

  public static PedistackErrorDescription SCHEDULED_BILL_TRANSACTION_UPDATE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Scheduled bill transaction cannot be updated. Please make sure you are the owner of the transaction",
          "Your transaction cannot be updated. Please try again");

  public static PedistackErrorDescription SCHEDULED_BILL_TRANSACTION_DELETE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Scheduled bill transaction cannot be deleted. Please make sure you are the owner of the transaction",
          "Your transaction cannot be removed. Please try again");

  public static PedistackErrorDescription TRANSACTION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Transaction not available. Please try again",
          "Your transaction cannot be found. Please try again");

  public static PedistackErrorDescription PROFILE_THRESHOLD_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Profile threshold already registered. Please try again",
          "This profile threshold is already registered. Please try again");

  public static PedistackErrorDescription PROFILE_THRESHOLD_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Profile threshold not found. Please try again",
          "Profile threshold not found. Please try again");

  public static PedistackErrorDescription SYSTEM_ACCOUNT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "System account already registered. Please try again",
          "The requested system account already registered. Please try again");

  public static PedistackErrorDescription SYSTEM_ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "System account not found. Please try again",
          "The requested system account not found. Please try again");

  public static PedistackErrorDescription FEE_ALREADY_CONFIGURED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Fee already configured. Please try again",
          "The system fee is already registered. Please try again");

  public static PedistackErrorDescription FEE_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Fee not found. Please try again", "The system fee is not registered. Please try again");

  public static PedistackErrorDescription QUOTE_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Quote not found. Please try again", "The quote cannot be registered. Please try again");

  public static PedistackErrorDescription RATING_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Commission already registered. Please try again",
          "The commission is already registered. Please try again");

  public static PedistackErrorDescription RATING_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Rating not found. Please try again", "The rating is not registered. Please try again");

  public static PedistackErrorDescription EXISTING_PAYMENT_REFERENCE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Existing payment reference. Please make sure payment reference is not a duplicate",
          "Duplicate payment reference. Please try again");

  public static PedistackErrorDescription CREDENTIAL_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Credential not found. Please try again",
          "The credential is not registered. Please try again");

  public static PedistackErrorDescription
      VIRTUAL_ACCOUNT_PROVIDER_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "Virtual account provider already registered. Please try again",
              "This virtual account provider is already registered. Please try again");

  public static PedistackErrorDescription VIRTUAL_ACCOUNT_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Virtual account provider not found. Please try again",
          "This virtual account provider is not registered. Please try again");

  public static PedistackErrorDescription ACCOUNT_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Account provider not found. Please try again",
          "This virtual account provider is not registered. Please try again");

  public static PedistackErrorDescription ACCOUNT_PROVIDER_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Account provider already registered. Please try again",
          "This virtual account provider is already registered. Please try again");

  public static PedistackErrorDescription
      FINANCIAL_INSTITUTION_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "Financial institution already. Please try again",
              "This financial institution is already registered. Please try again");

  public static PedistackErrorDescription FINANCIAL_INSTITUTION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Financial institution not registered. Please try again",
          "This financial institution is not registered. Please try again");

  public static PedistackErrorDescription APPROVAL_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Transaction approval not registered. Please try again",
          "This transaction approval is not registered. Please try again");

  public static PedistackErrorDescription APPROVAL_ALREADY_APPROVED_OR_REJECTED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Transaction approval already approved or rejected. Please try again",
          "This transaction approval was already approved or rejected. Please try again");

  public static PedistackErrorDescription WITHDRAWAL_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Withdrawal not found. Please try again",
          "The withdrawal information cannot be registered. Please try again");

  public static PedistackErrorDescription
      FINANCIAL_INSTRUMENT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "Financial instrument already registered. Please try again",
              "The financial instrument is already registered. Please try again");

  public static PedistackErrorDescription FINANCIAL_INSTRUMENT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Financial instrument not found. Please try again",
          "The financial instrument is not registered. Please try again");

  public static PedistackErrorDescription ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Account not found. Please try again", "The account is not registered. Please try again");

  public static PedistackErrorDescription TRANSACTION_METRIC_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Transaction metric not found. Please try again",
          "The transaction metric is not registered. Please try again");

  public static PedistackErrorDescription COMMISSION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Commission not found. Please try again",
          "The commission is not registered. Please try again");

  public static PedistackErrorDescription COMMISSION_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Commission already registered. Please try again",
          "The commission is already registered. Please try again");

  public static PedistackErrorDescription COMMISSION_SPLIT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Commission split configuration not found. Please try again",
          "The commission split configuration is not registered. Please try again");

  public static PedistackErrorDescription COMMISSION_SPLIT_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Commission split configuration already registered. Please try again",
          "The commission split is already registered. Please try again");

  public static PedistackErrorDescription PARTNER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Partner not found. Please try again", "The partner is not registered. Please try again");

  public static PedistackErrorDescription
      EMAIL_ADDRESS_OR_MOBILE_NUMBER_REQUIRED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "Email address and/or mobile number required for this operation",
              "To fulfil this operation, email address or mobile number is required");

  public static PedistackErrorDescription FX_RATE_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "FX rate not available. Please try again",
          "The FX rate is not available. Please try again");

  public static PedistackErrorDescription FX_RATE_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "FX rate already registered. Please try again",
          "The FX rate already registered. Please try again");

  public static PedistackErrorDescription BILL_TRANSACTION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Bill transaction not found. Please try again",
          "The bill transaction is not registered. Please try again");

  public static PedistackErrorDescription HTTP_COMMUNICATION_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "HTTP Communication error. Please try again",
          "Unable to initiate HTTP communication to endpoint. Please try again");

  public static PedistackErrorDescription QUOTE_NOT_SUPPORTED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Quote not supported. Please try again",
          "Unable to support quote operation. Please try again");

  public static PedistackErrorDescription DEFAULT_SETTLEMENT_CURRENCY_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Default settlement currency not found",
          "The default settlement currency is not registered. Please try again");

  public static PedistackErrorDescription BILL_AMOUNT_MISMATCH_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Bill amount mismatch. Please try again", "The bill amount is matched. Please try again");

  public static PedistackErrorDescription ADMIN_USER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Admin user not found. Please try again",
          "The admin user is not available. Please try again");

  public static PedistackErrorDescription CUSTOMER_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Customer not found. Please try again",
          "The customer information is not available. Please try again");

  public static PedistackErrorDescription UPLOAD_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unable to complete upload. Please try again",
          "The upload process cannot be completed. Please try again");

  public static PedistackErrorDescription TRANSACTION_WATCH_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Transaction watch not found. Please try again",
          "The transaction watch not registered. Please try again");

  public static PedistackErrorDescription IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Identity information not found. Please try again",
          "The identity information is not registered. Please try again");

  public static PedistackErrorDescription PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Personal information not found. Please try again",
          "The personal information is not registered. Please try again");

  public static PedistackErrorDescription BUSINESS_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Business information not found. Please try again",
          "The business information is not registered. Please try again");

  public static PedistackErrorDescription
      BUSINESS_INFORMATION_ALREADY_REGISTERED_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
              "Business information already registered. Please try again",
              "The business information is already registered. Please try again");

  public static PedistackErrorDescription ADDRESS_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Address information not found. Please try again",
          "The address information is not registered. Please try again");

  public static PedistackErrorDescription SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Social media information not found. Please try again",
          "The social media information is not registered. Please try again");

  public static PedistackErrorDescription FACEBOOK_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Facebook username already registered. Please try again",
          "The Facebook username is already registered. Please try again");

  public static PedistackErrorDescription TWITTER_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Twitter username already registered. Please try again",
          "The twitter username is already registered. Please try again");

  public static PedistackErrorDescription IG_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Instagram username already registered. Please try again",
          "The instagram username is already registered. Please try again");

  public static PedistackErrorDescription LINKEDIN_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "LinkedIn username already registered. Please try again",
          "The LinkedIn username is already registered. Please try again");

  public static PedistackErrorDescription MEDIUM_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Medium username already registered. Please try again",
          "The Medium username is already registered. Please try again");

  public static PedistackErrorDescription ISSUE_DATE_AFTER_EXPIRY_DATE_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "The identification issue date is after the expiry date. Please try again",
          "The identification issue date is after the expiry date. Please try again");

  public static PedistackErrorDescription UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Unauthorized access. Please try again",
          "Access to this resource is not authorized. Please try again");

  public static PedistackErrorDescription CARD_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Card not found. Please try again",
          "The card information is not available. Please try again");

  public static PedistackErrorDescription CARD_ALREADY_REGISTERED_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Card already registered. Please try again",
          "The card information is already registered. Please try again");

  public static PedistackErrorDescription GRANT_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Grant is required. Please try again", "The grant is not found. Please try again");

  public static PedistackErrorDescription NEXT_OF_KIN_NOT_FOUND_ERROR_DESCRIPTION =
      PedistackErrorDescription.createDescription(
          "Next of kin information not found. Please try again",
          "The next of kin information is not found. Please try again");

  public static PedistackErrorDescription COMMUNICATION_ADDRESS_SIZE_LIMIT_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
                  "Communication address size limit exceeded. Please try again",
                  "The limit of the communication address is exceeded. Please try again");

  public static PedistackErrorDescription POSTAL_ADDRESS_SIZE_LIMIT_ERROR_DESCRIPTION =
          PedistackErrorDescription.createDescription(
                  "Postal address size limit exceeded. Please try again",
                  "The limit of the postal address is exceeded. Please try again");


}
