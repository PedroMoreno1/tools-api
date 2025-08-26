package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.ticket.TicketCodeRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.TicketResponseDTO;
import com.empresa.toolsapi.entity.Person;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.entity.Ticket;
import com.empresa.toolsapi.enums.TicketStatus;
import com.empresa.toolsapi.enums.ToolStatus;
import com.empresa.toolsapi.mapper.ToolTicketMapper;
import com.empresa.toolsapi.repository.PersonRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.repository.ToolTicketRepository;
import com.empresa.toolsapi.service.TicketService;
import com.empresa.toolsapi.utils.AppSettings;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final ToolTicketRepository repoTicket;
    private final ToolRepository repoTool;
    private final PersonRepository repoPerson;


    @Override
    public TicketResponseDTO createTicket(TicketRequestDTO dto) {

        Tool existsTool = repoTool.findById(dto.getIdTool())
                .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada"));

        Person existsPerson = repoPerson.findByDni(dto.getDni())
                .orElseThrow(() -> new EntityNotFoundException("Persona no registrada"));

        boolean isPending = repoTicket.existsByToolAndStatus(existsTool, TicketStatus.PENDING);
        if (isPending) {
            throw new RuntimeException("La herramienta ya esta prestada");
        }
        //Generando Ticket
        Ticket newTicket = Ticket.builder()
                .tool(existsTool)
                .person(existsPerson)
                .borrowedAt(LocalDateTime.now())
                .ticketCode(generarteTicketCode())
                .status(TicketStatus.PENDING)
                .build();

        //Actualizando estado y asignando el ticket a la herramienta correspondiente
        existsTool.setStatus(ToolStatus.BORROWED);
        existsTool.setTicketCode(newTicket.getTicketCode());

        repoTicket.save(newTicket);
        repoTool.save(existsTool);

        return ToolTicketMapper.toResponseDTO(newTicket);
    }

    @Override
    public TicketResponseDTO returnTool(TicketCodeRequestDTO requestDTO) {
        Ticket ticket = repoTicket.findByTicketCode(requestDTO.getTicketCode())
                .orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado"));

        if(ticket.getStatus() == TicketStatus.RETURNED){
            throw new EntityNotFoundException("La herramienta ya fue regresada");
        }

        ticket.setReturnedAt(LocalDateTime.now());
        ticket.setStatus(TicketStatus.RETURNED);

        //Actualizamos el estado y quitamos el ticket de la herramienta
        Tool tool = ticket.getTool();
        tool.setStatus(ToolStatus.IN_STORE);
        tool.setTicketCode(AppSettings.NOT_TICKET);

        repoTool.save(tool);
        repoTicket.save(ticket);

        return ToolTicketMapper.toResponseDTO(ticket);
    }


    @Override
    public List<TicketResponseDTO> getAllTickets() {
        return List.of();
    }

    private String generarteTicketCode(){
        return "TCK-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0,8);
    }

    //IMPLEMENTAR ELIMINACION LOGICA CON STATUS
}
