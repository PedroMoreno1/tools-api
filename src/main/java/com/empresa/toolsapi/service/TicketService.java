package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.ticket.ticketCreation.request.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.response.TicketResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.DetailsResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.ReturnToolRequestDTO;

import java.util.List;

public interface TicketService {

    TicketResponseDTO createTicket(TicketRequestDTO dto);
    TicketResponseDTO getByTicketCode(String ticketCode);
    DetailsResponseDTO returnToolAndCloseTicket(ReturnToolRequestDTO returnDTO);
    List<TicketResponseDTO> findAllTickets();

}
