package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.ticket.TicketResponseDTO;
import com.empresa.toolsapi.entity.Person;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.entity.Ticket;
import com.empresa.toolsapi.enums.TicketStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToolTicketMapper {

    public static TicketResponseDTO toResponseDTO(Ticket ticket){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String borrowedAtFormatted = ticket.getBorrowedAt() != null ? ticket.getBorrowedAt().format(formatter) : null;
        String returnedAtFormatted = ticket.getReturnedAt() != null ? ticket.getReturnedAt().format(formatter) : null;

        return TicketResponseDTO.builder()
                .ticketCode(ticket.getTicketCode())
                .toolName(ticket.getTool().getName())
                .personName(ticket.getPerson().getName())
                .personDni(ticket.getPerson().getDni())
                .borrowedAt(borrowedAtFormatted)
                .returnedAt(returnedAtFormatted)
                .status(ticket.getStatus())
                .build();
    }

    /**
     * Explicacion ReturnedAT
     * Cuando se crea un ticket no se agrega fecha de retorno, porque logicamente aun no esta devuelta
     * esto se guarda como null, entonces a null no se le puede aplicar el .format(), por ello que al crear el ticket
     * me generaba un nullPointerException pero si se creaba el ticket.
     *
     * Ahora lo que se hace es que si el ticket NO es null se aplica el .format()
     * si es null se deja asi.
     *
     * Operador Ternario: condición ? valorSiVerdadero : valorSiFalso --- equivalente a: if(){}else{}
     */

    public static Ticket toEntity(String tickedCode, Tool tool, Person person){
        return Ticket.builder()
                .ticketCode(tickedCode)
                .tool(tool)
                .person(person)
                .borrowedAt(LocalDateTime.now())
                .status(TicketStatus.PENDING)
                .build();
    }
}
