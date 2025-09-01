package com.empresa.toolsapi.dto.ticket;

import com.empresa.toolsapi.enums.TicketStatus;
import lombok.*;

//CERRAR TICKET -- RESPUESTA
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailsResponseDTO {
    private Long idDetails;
    private String ticketCode;
    private String personName;
    private String personDni;
    private TicketStatus status;
    private PersonReturnDTO deliveredBy;
    private String note;
}
