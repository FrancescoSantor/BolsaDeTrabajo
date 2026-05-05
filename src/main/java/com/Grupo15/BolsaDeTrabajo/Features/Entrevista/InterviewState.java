package com.Grupo15.BolsaDeTrabajo.Features.Entrevista;

public enum InterviewState {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    WAITING("Waiting");

    private final String label;

    InterviewState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
