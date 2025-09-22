package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.ticket.ticketReturn.DetailsResponseDTO;
import com.empresa.toolsapi.dto.ticket.ticketReturn.PersonReturnDTO;
import com.empresa.toolsapi.entity.ReturnDetails;
import com.empresa.toolsapi.entity.Ticket;

public class ReturnDetailsMapper {

    public static DetailsResponseDTO toResponseDTO (Ticket ticket, ReturnDetails returnDetails){

        PersonReturnDTO personReturnDTO = PersonReturnDTO.builder()
                .namePerson(returnDetails.getReturnedByName())
                .dniPerson(returnDetails.getReturnedByDni())
                .build();

        return DetailsResponseDTO.builder()
                .idDetails(returnDetails.getIdDetails())
                .ticketCode(ticket.getTicketCode())
                .deliveredBy(personReturnDTO)
                .note(returnDetails.getNote())
                .build();

    }

}
