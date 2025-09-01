package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.ticket.*;
import com.empresa.toolsapi.entity.Person;
import com.empresa.toolsapi.entity.ReturnDetails;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.entity.Ticket;
import com.empresa.toolsapi.enums.TicketStatus;
import com.empresa.toolsapi.enums.ToolStatus;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.mapper.ReturnDetailsMapper;
import com.empresa.toolsapi.mapper.ToolTicketMapper;
import com.empresa.toolsapi.repository.PersonRepository;
import com.empresa.toolsapi.repository.ReturnDetailsRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.repository.ToolTicketRepository;
import com.empresa.toolsapi.service.TicketService;
import com.empresa.toolsapi.utils.AppSettings;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final ToolTicketRepository repoTicket;
    private final ToolRepository repoTool;
    private final PersonRepository repoPerson;
    private final ReturnDetailsRepository returnDetailsRepo;


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
        //Crear ticket
        Ticket newTicket = Ticket.builder()
                .tool(existsTool)
                .person(existsPerson)
                .borrowedAt(LocalDateTime.now())
                .ticketCode(generarteTicketCode())
                .status(TicketStatus.PENDING)
                .isDeleted(false)
                .build();

        //Actualizar estado y asignando el ticket a la herramienta correspondiente
        existsTool.setStatus(ToolStatus.BORROWED);
        existsTool.setTicketCode(newTicket.getTicketCode());

        repoTicket.save(newTicket);
        repoTool.save(existsTool);

        return ToolTicketMapper.toResponseDTO(newTicket);
    }

    @Override
    public TicketResponseDTO getByTicketCode(String ticketCode) {

        Ticket ticket = repoTicket.findByTicketCode(ticketCode)
                .orElseThrow(() -> new ResourceNotFoundException("Código de Ticket no existe"));

        return ToolTicketMapper.toResponseDTO(ticket);
    }

    @Override
    @Transactional
    public DetailsResponseDTO returnTool(ReturnToolRequestDTO returnDTO) {

        //Buscar ticket por id
        Ticket ticket = repoTicket.findById(returnDTO.getIdTicket())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no existe"));

        //Ticket fue devuelto o esta eliminado
        if (ticket.getStatus() == TicketStatus.RETURNED || ticket.isDeleted()){
            throw new BadRequestException("Ticket no disponible");
        }
        //--- Tool del Ticket ---
        //Crear obj con la herramienta del ticket
        Tool tool = ticket.getTool();
        //Cambiar estado y eliminar codigo de ticket
        tool.setStatus(ToolStatus.IN_STORE);
        tool.setTicketCode(AppSettings.NOT_TICKET);

        //--- Ticket ---
        //Asignar fecha de retorno y cambiar estado
        ticket.setReturnedAt(LocalDateTime.now());
        ticket.setStatus(TicketStatus.RETURNED);

        //--- ReturnDetails ---
        //Crear obj, asignar ticket para la relacion y datos.
        ReturnDetails returnDetails = new ReturnDetails();
        returnDetails.setTicket(ticket);
        returnDetails.setNote(returnDTO.getNote());
        returnDetails.setDeliveredBy(returnDTO.getDeliveredBy().getNamePerson());
        returnDetails.setDniPerson(returnDTO.getDeliveredBy().getDniPerson());

        //Guardar
        repoTool.save(tool);
        repoTicket.save(ticket);
        returnDetailsRepo.save(returnDetails);

        return ReturnDetailsMapper.toResponseDTO(ticket, returnDetails);
    }


    private String generarteTicketCode(){
        return "TCK-" + UUID.randomUUID().toString().substring(0,12).toUpperCase();
    }

    //IMPLEMENTAR ELIMINACION LOGICA CON STATUS
}
