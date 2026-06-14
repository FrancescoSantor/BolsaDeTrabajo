package com.Grupo15.BolsaDeTrabajo.Features.Offer;

public enum OfferType {
    IN_PERSON("In_Person"),
    VIRTUAL("Virtual"),
    BOTH("Both");

    private final String label;

    OfferType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
