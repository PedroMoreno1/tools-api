package com.empresa.toolsapi.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketCodeRequestDTO {
    @JsonProperty("ticket_code")
    @NotBlank(message = "El código del ticket no puede estar vacío")
    private String ticketCode;
}
