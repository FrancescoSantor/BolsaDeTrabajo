package com.Grupo15.BolsaDeTrabajo.Features.Commons;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "external_id", nullable = false, unique = true, updatable = false)
    private UUID externalId;

    @PrePersist
    public void generateExternalId() {
        if (externalId == null) {
            externalId = UUID.randomUUID();
        }
    }
}
