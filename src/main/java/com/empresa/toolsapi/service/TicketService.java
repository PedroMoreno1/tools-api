package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.ticket.TicketCodeRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketResponseDTO;
import java.util.List;

public interface TicketService {

    TicketResponseDTO createTicket(TicketRequestDTO dto);
    TicketResponseDTO returnTool(TicketCodeRequestDTO codeRequestDTO);
    List<TicketResponseDTO> getAllTickets();
}
