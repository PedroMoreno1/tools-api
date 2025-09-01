package com.empresa.toolsapi.dto.ticket;

import com.empresa.toolsapi.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private Long idTicket;
    private String ticketCode;
    private String toolName;
    private String personName;
    private String personDni;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String borrowedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String returnedAt;
    private TicketStatus status;
}
