package com.empresa.toolsapi.dto.ticket.ticketReturn;

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
    private PersonReturnDTO deliveredBy;
    private String note;
}
