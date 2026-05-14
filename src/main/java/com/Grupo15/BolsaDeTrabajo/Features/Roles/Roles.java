package com.Grupo15.BolsaDeTrabajo.Features.Roles;

public enum Roles {
    CANDIDATE("Candidate"),
    COMPANY("Company");

    private final String label;
    Roles(String label){
        this.label = label;
    }
}
