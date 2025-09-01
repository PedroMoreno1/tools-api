package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.ticket.*;

public interface TicketService {

    TicketResponseDTO createTicket(TicketRequestDTO dto);
    TicketResponseDTO getByTicketCode(String ticketCode);
    DetailsResponseDTO returnTool(ReturnToolRequestDTO returnDTO);

}
