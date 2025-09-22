package com.empresa.toolsapi.dto.ticket.ticketReturn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//CERRAR TICKET -- SOLICITUD

@Getter @Setter
public class ReturnToolRequestDTO {

    @NotNull(message = "El id no puede ser nulo")
    private Long idTicket;
    @Valid //verifica que los datos internos tambien sean validados segun su anotacion
    private PersonReturnDTO deliveredBy;
    private String note;
}
