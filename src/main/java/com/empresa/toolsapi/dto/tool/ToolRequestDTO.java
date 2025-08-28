package com.empresa.toolsapi.dto.tool;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class ToolRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre debe tener como máximo 100 caracteres")
    private String name;
    private String description;
    @NotBlank(message = "La URL de imagen no puede estar vacia")
    @URL(protocol = "http", message = "Debe ser una URL valida")
    private String urlImg;
    @NotNull
    private Long idSection;
    @NotNull
    private Long idCategory;
}
