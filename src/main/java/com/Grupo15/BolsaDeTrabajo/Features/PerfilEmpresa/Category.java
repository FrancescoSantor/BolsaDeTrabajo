package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

public enum Category {
    TECHNOLOGY("Technology"),
    HEALTHCARE("Healthcare"),
    EDUCATION("Education"),
    FINANCE("Finance"),
    CONSTRUCTION("Construction"),
    MARKETING("Marketing"),
    HUMAN_RESOURCES("Human_Resources"),
    LOGISTICS("Logistics"),
    TRANSPORTATION("Transportation"),
    AGRICULTURE("Agriculture"),
    MEDIA("Media"),
    ENERGY("Energy"),
    TELECOMMUNICATION("Telecomunication"),
    SERVICE("Service"),
    LEGAL("Legal"),
    TOURISM("Tourism"),
    INDUSTRY("Industry"),
    GASTRONOMIC("Gastronomic"),
    OTHER("Other");

    private final String label;

    Category(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
