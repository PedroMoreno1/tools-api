package com.empresa.toolsapi.dto.ticket;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//CERRAR TICKET -- SOLICITUD

@Getter @Setter
public class ReturnToolRequestDTO {

    @NotNull(message = "El id no puede ser nulo")
    private Long idTicket;
    private PersonReturnDTO deliveredBy;
    private String note;
}
