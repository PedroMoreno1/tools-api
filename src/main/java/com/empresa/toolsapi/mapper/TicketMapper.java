package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.ticket.ticketCreation.response.TicketResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketCreation.response.ToolRentedResponseDTO;
import com.empresa.toolsapi.entity.Ticket;
import com.empresa.toolsapi.entity.TicketTool;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketMapper {

    public static TicketResponseDTO toResponseDTO(Ticket ticket){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String rentDateFormatted = ticket.getRentedDate() != null ? ticket.getRentedDate().format(formatter) : null;

        List<ToolRentedResponseDTO> tools = ticket.getTicketTools().stream()
                .map(ticketTool -> ToolRentedResponseDTO.builder()
                        .idTool(ticketTool.getTool().getIdTool())
                        .toolName(ticketTool.getTool().getName())
                        .rentedQuantity(ticketTool.getQuantity())
                        .rentalCostUnit(ticketTool.getTool().getRentalPrice())
                        .totalCostRent(ticketTool.getTotalCostRent())
                        .build())
                .toList();

        BigDecimal totalCost = ticket.getTicketTools().stream()
                .map(TicketTool::getTotalCostRent)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return TicketResponseDTO.builder()
                .idTicket(ticket.getIdTicket())
                .ticketCode(ticket.getTicketCode())
                .customerName(ticket.getCustomer().getFullName())
                .customerDni(ticket.getCustomer().getDni())
                .rentedDate(rentDateFormatted)
                .tools(tools)
                .totalCost(totalCost)
                .build();
    }

    /**
     * Explicacion returnedDate
     * Cuando se crea un ticket no se agrega fecha de retorno, porque logicamente aun no esta devuelta
     * esto se guarda como null, entonces a null no se le puede aplicar el .format(), por ello que al crear el ticket
     * me generaba un nullPointerException pero si se creaba el ticket.
     *
     * Ahora lo que se hace es que si el ticket NO es null se aplica el .format()
     * si es null se deja asi.
     *
     * Operador Ternario: condición ? valorSiVerdadero : valorSiFalso --- equivalente a: if(){}else{}
     */

}
