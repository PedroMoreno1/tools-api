package com.empresa.toolsapi.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

    //Cuando en el json ingresaba "id_tool" no me reconocia porque esperaba "idTool"(no romper con el snake_case)
    @JsonProperty("id_tool")
    @NotNull(message = "El id de la Herramienta es obligatorio")
    private Long idTool;
    @NotBlank(message = "El dni es requerido")
    @Max(value = 8, message = "max 8 digitos")
    private String dni;
}
