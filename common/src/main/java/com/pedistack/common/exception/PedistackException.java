package com.pedistack.common.exception;

public final class PedistackException extends Exception {

  private final PedistackErrorCodes errorCode;
  private final PedistackErrorDescription pedistackErrorDescription;

  public PedistackException(
          PedistackErrorCodes errorCode, PedistackErrorDescription pedistackErrorDescription) {
    super(pedistackErrorDescription.getDescription());
    this.errorCode = errorCode;
    this.pedistackErrorDescription = pedistackErrorDescription;
  }

  public static PedistackException createInternalErrorException(
      PedistackErrorDescription pedistackErrorDescription) {
    return new PedistackException(PedistackErrorCodes.INTERNAL_ERROR, pedistackErrorDescription);
  }

  public static PedistackException createUnauthorizedException(
      PedistackErrorDescription pedistackErrorDescription) {
    return new PedistackException(PedistackErrorCodes.UNAUTHORIZED, pedistackErrorDescription);
  }

  public static PedistackException createBadRequestException(
      PedistackErrorDescription pedistackErrorDescription) {
    return new PedistackException(PedistackErrorCodes.BAD_REQUEST, pedistackErrorDescription);
  }

  public static PedistackException communicationException(
      PedistackErrorDescription pedistackErrorDescription) {
    return new PedistackException(
        PedistackErrorCodes.COMMUNICATION_ERROR, pedistackErrorDescription);
  }

  public static PedistackException accessExpiredException(
      PedistackErrorDescription pedistackErrorDescription) {
    return new PedistackException(PedistackErrorCodes.ACCESS_EXPIRED, pedistackErrorDescription);
  }

  public PedistackErrorCodes getErrorCode() {
    return errorCode;
  }

  public PedistackErrorDescription getBillErrorDescription() {
    return pedistackErrorDescription;
  }
}
