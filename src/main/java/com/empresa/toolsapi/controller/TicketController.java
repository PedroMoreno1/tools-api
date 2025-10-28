package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.ticket.ticketCreation.request.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.response.TicketResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.DetailsResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.ReturnToolRequestDTO;
import com.empresa.toolsapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/ticket")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO requestDTO){

        TicketResponseDTO ticket = ticketService.createTicket(requestDTO);

        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/getTicketCode")
    public ResponseEntity<TicketResponseDTO> getByTicketCode(@RequestParam String ticketCode){

        TicketResponseDTO getTicket = ticketService.getByTicketCode(ticketCode);

        return ResponseEntity.ok(getTicket);
    }

    @PatchMapping("/returnToolAndCloseTicket")
    public ResponseEntity<DetailsResponseDTO> returnToolAndCloseTicket(@Valid @RequestBody ReturnToolRequestDTO returnRequestDTO){

        DetailsResponseDTO detailsResponseDTO = ticketService.returnToolAndCloseTicket(returnRequestDTO);

        return ResponseEntity.ok(detailsResponseDTO);

    }
    @GetMapping("/list")
    public ResponseEntity<List<TicketResponseDTO>> findAllTickets(){

        List<TicketResponseDTO> tickets = ticketService.findAllTickets();

        return ResponseEntity.ok(tickets);
    }
    /**
     * Se usa PatchMapping porque se hace una actualizacion parcial del ticket(fecha de retorno).
     * Se guarda una nueva entidad asociada(return details).
     */
}
