package com.empresa.toolsapi.dto.section.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SectionRequestDTO {
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "Maximo 50 caracteres")
    private String name;
    @Size(max = 700, message = "Cantidad de caracteres excedida")
    private String description;
}
