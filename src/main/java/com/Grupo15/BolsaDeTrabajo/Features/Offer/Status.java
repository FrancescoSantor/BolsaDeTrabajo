package com.Grupo15.BolsaDeTrabajo.Features.Offer;

public enum Status {
    OPEN("Open"),
    CLOSE("Close");
    private final String label;
    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
