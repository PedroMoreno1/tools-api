package com.empresa.toolsapi.dto.ticket.ticketCreation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@Builder
public class ToolRentedResponseDTO {

    private Long idTool;
    private String toolName;
    private int rentedQuantity;
    private BigDecimal rentalCostUnit;
    private BigDecimal totalCostRent;
}
