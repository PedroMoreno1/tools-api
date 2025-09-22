package com.empresa.toolsapi.dto.ticket.ticketCreation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

    @NotBlank(message = "El dni es requerido")
    @Size(max = 8, message = "El dni debe tener como maximo 8 digitos.")
    @Pattern(regexp = "\\d+", message = "El campo solo debe contener digitos.")
    @JsonProperty("dni")
    private String dni;

    @JsonProperty("tools")
    private List<ToolRentRequestDTO> tools;
}
