package com.empresa.toolsapi.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@Embeddable //Para incrustarse como ID, no es una entidad
public class TicketToolPK implements Serializable {
    private Long ticketId;
    private Long toolId;
}
