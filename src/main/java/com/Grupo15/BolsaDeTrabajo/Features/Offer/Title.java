package com.Grupo15.BolsaDeTrabajo.Features.Offer;

public enum Title {
    SECONDARY("Secondary"),
    BACHELOR("Bachelor"),
    LICENSED("Licensed"),
    TECHNICAL("Technical"),
    POSTGRADUATE("Postgraduate"),
    MASTER("Master"),
    DOCTORATE("Doctorate"),
    ENGINEER("Engineer"),
    OTHER("Other"),
    NONE("None");

    private final String label;

    Title(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
