package com.empresa.toolsapi.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryRequestDTO {
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "Maximo 50 caracteres")
    private String name;
}
