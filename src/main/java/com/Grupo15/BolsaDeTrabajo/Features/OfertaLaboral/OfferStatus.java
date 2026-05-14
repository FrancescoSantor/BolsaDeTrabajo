package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral;

public enum OfferStatus {
    OPEN("Open"),
    CLOSE("Close");
    private final String label;
    OfferStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
