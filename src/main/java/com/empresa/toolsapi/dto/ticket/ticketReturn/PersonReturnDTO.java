package com.empresa.toolsapi.dto.ticket.ticketReturn;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

// Información de la persona que realiza la devolución (anidada en el JSON)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonReturnDTO {

    @NotBlank(message = "El nombre de la persona que devolvio es requerido")
    private String namePerson;
    @NotBlank(message = "El dni de la persona que devolvio es requrido")
    @Size(max = 8, message = "El dni debe tener como maximo 8 digitos.")
    @Pattern(regexp = "\\d+", message = "El campo solo debe contener digitos.")
    private String dniPerson;
}

