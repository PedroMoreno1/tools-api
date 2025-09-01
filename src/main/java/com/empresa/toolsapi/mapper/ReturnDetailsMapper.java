package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.ticket.DetailsResponseDTO;
import com.empresa.toolsapi.dto.ticket.PersonReturnDTO;
import com.empresa.toolsapi.entity.ReturnDetails;
import com.empresa.toolsapi.entity.Ticket;

public class ReturnDetailsMapper {

    public static DetailsResponseDTO toResponseDTO (Ticket ticket, ReturnDetails returnDetails){

        PersonReturnDTO personReturnDTO = PersonReturnDTO.builder()
                .namePerson(returnDetails.getDeliveredBy())
                .dniPerson(returnDetails.getDniPerson())
                .build();

        return DetailsResponseDTO.builder()
                .idDetails(returnDetails.getIdDetails())
                .ticketCode(ticket.getTicketCode())
                .personName(ticket.getPerson().getName())
                .personDni(ticket.getPerson().getDni())
                .status(ticket.getStatus())
                .deliveredBy(personReturnDTO)
                .note(returnDetails.getNote())
                .build();
    }
}
