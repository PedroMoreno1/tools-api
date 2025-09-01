package com.empresa.toolsapi.dto.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

    //Cuando en el json ingresaba "id_tool" no me reconocia porque esperaba "idTool"(no romper con el snake_case)
    @JsonProperty("id_tool")
    @NotNull(message = "El ID de la Herramienta es obligatorio.")
    private Long idTool;
    @NotBlank(message = "El dni es requerido")
    @Size(max = 8, message = "El dni debe tener como maximo 8 digitos.")
    @Pattern(regexp = "\\d+", message = "El campo solo debe contener digitos.")
    @JsonProperty("dni")
    private String dni;
}
