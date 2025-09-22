package com.empresa.toolsapi.dto.ticket;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
public class TicketBasicDTO {

    private Long idTicket;
    private String ticketCode;
    private String customerName;
    private String customerDni;

}
