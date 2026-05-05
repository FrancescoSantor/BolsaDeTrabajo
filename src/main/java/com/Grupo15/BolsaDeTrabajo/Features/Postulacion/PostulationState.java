package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

public enum PostulationState {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    WAITING("Waiting");

    private final String label;
    PostulationState(String label) {
        this.label = label;
    }
    
}
