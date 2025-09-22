package com.empresa.toolsapi.dto.ticket.ticketCreation.response;

import com.empresa.toolsapi.dto.ticket.TicketBasicDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.request.ToolRentRequestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@SuperBuilder
public class TicketResponseDTO extends TicketBasicDTO {

    private String rentedDate;
    private List<ToolRentedResponseDTO> tools;
    private BigDecimal totalCost;
}
