package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.ticket.TicketCodeRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketResponseDTO;
import com.empresa.toolsapi.service.TicketService;
import com.empresa.toolsapi.utils.AppSettings;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ticket")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/generate")
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO requestDTO){

        TicketResponseDTO ticket = ticketService.createTicket(requestDTO);

        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/return")
    public ResponseEntity<TicketResponseDTO> returnTool(@RequestBody TicketCodeRequestDTO requestDTO){

        TicketResponseDTO returnTool = ticketService.returnTool(requestDTO);

        return ResponseEntity.ok(returnTool);
    }
}
