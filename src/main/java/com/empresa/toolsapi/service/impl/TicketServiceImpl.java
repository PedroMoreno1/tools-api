package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.ticket.ticketCreation.request.ToolRentRequestDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.request.TicketRequestDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.response.TicketResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.DetailsResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.ReturnToolRequestDTO;
import com.empresa.toolsapi.entity.*;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.exception.ToolNotAvailable;
import com.empresa.toolsapi.mapper.ReturnDetailsMapper;
import com.empresa.toolsapi.mapper.TicketMapper;
import com.empresa.toolsapi.repository.ReturnDetailsRepository;
import com.empresa.toolsapi.repository.TicketToolRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.repository.TicketRepository;
import com.empresa.toolsapi.service.TicketService;
import com.empresa.toolsapi.utils.ErrorMessages;
import com.empresa.toolsapi.validation.CustomerValidation;
import com.empresa.toolsapi.validation.TicketValidation;
import com.empresa.toolsapi.validation.ToolValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repoTicket;
    private final ToolRepository repoTool;
    private final CustomerValidation customerValidation;
    private final ToolValidation toolValidation;
    private final TicketToolRepository repoTicketTool;
    private final ReturnDetailsRepository repoReturnDetails;
    private final TicketValidation ticketValidation;

    @Override
    @Transactional
    public TicketResponseDTO createTicket(TicketRequestDTO dto){

        if (dto.getTools().size() > 2){
            throw new BadRequestException("Maximo 2 variedades de herramientas");
        }

        Customer customer = customerValidation.existsDni(dto.getDni());

        //Crear ticket
        Ticket ticket = Ticket.builder()
                .ticketCode(generateTicketCode())
                .customer(customer)
                .rentedDate(LocalDateTime.now())
                .build();

        List<TicketTool> ticketTools = new ArrayList<>();
        List<Tool> toolsUpdate = new ArrayList<>();

        for (ToolRentRequestDTO toolRented : dto.getTools()){

            Tool tool = toolValidation.existsTool(toolRented.getIdTool());

            if (toolRented.getRentedQuantity() > tool.getAvailableQuantity()){
                throw new ToolNotAvailable(ErrorMessages.QUANTITY_NOT_AVAILABLE);
            }

            BigDecimal totalCost = ticketValidation.totalCost(toolRented.getRentedQuantity(), tool.getRentalPrice());

            TicketTool ticketTool = new TicketTool(ticket, tool);
            ticketTool.setQuantity(toolRented.getRentedQuantity());
            ticketTool.setRentalCostUnit(tool.getRentalPrice());
            ticketTool.setTotalCostRent(totalCost);

            tool.setAvailableQuantity(ticketValidation.totalAvailable(toolRented.getRentedQuantity(), tool.getAvailableQuantity()));

            toolsUpdate.add(tool);
            ticketTools.add(ticketTool);

        }

        repoTicket.save(ticket);
        repoTool.saveAll(toolsUpdate);
        repoTicketTool.saveAll(ticketTools);//relacion en bd, se puede evitar usado cascade persist

        ticket.setTicketTools(ticketTools); //relacion en memoria

        return TicketMapper.toResponseDTO(ticket);
    }

    @Override
    public TicketResponseDTO getByTicketCode(String ticketCode) {

        Ticket ticket = repoTicket.findByTicketCode(ticketCode)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.TICKET_CODE_NOT_EXISTS, ticketCode)));

        return TicketMapper.toResponseDTO(ticket);
    }

    @Override
    @Transactional
    public DetailsResponseDTO returnToolAndCloseTicket(ReturnToolRequestDTO returnDTO) {

        //Buscar ticket por id
        Ticket ticket = repoTicket.findById(returnDTO.getIdTicket())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.TICKET_NOT_FOUND));

        if(repoReturnDetails.existsByTicket_IdTicket(ticket.getIdTicket())){
            throw new ResourceNotFoundException(ErrorMessages.TICKET_CLOSED);
        }

        ReturnDetails rd = new ReturnDetails();
        rd.setNote(returnDTO.getNote());
        rd.setReturnedByName(returnDTO.getDeliveredBy().getNamePerson());
        rd.setReturnedByDni(returnDTO.getDeliveredBy().getDniPerson());
        rd.setTicket(ticket);

        //--- Ticket ---
        ticket.setReturnedDate(LocalDateTime.now());

        //Guardar
        repoTicket.save(ticket);
        repoReturnDetails.save(rd);

        return ReturnDetailsMapper.toResponseDTO(ticket, rd);

    }

    @Override
    public List<TicketResponseDTO> findAllTickets() {

        return repoTicket.findAll().stream()
                .map(TicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    private String generateTicketCode(){
        return "TCK-" + UUID.randomUUID().toString().substring(0,12).toUpperCase();
    }

}
