package com.Grupo15.BolsaDeTrabajo.Features.Entrevista;

public enum InterviewStatus {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    WAITING("Waiting");

    private final String label;

    InterviewStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
