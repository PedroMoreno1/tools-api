package com.empresa.toolsapi.dto.ticket;

import jakarta.validation.constraints.NotBlank;
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
    private String dniPerson;
}

