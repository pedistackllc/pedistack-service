package com.pedistack.common.exception;

public final class PedistackErrorDescription {

  private final String description;
  private final String detailedDescription;

  public PedistackErrorDescription(String description, String detailedDescription) {
    this.description = description;
    this.detailedDescription = detailedDescription;
  }

  public static PedistackErrorDescription createDescription(
      String description, String detailedDescription) {
    return new PedistackErrorDescription(description, detailedDescription);
  }

  public String getDescription() {
    return description;
  }

  public String getDetailedDescription() {
    return detailedDescription;
  }
}
