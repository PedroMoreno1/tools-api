package com.empresa.toolsapi.dto.ticket.ticketCreation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@Builder
public class ToolRentRequestDTO { //Se necesita solo id y cantidad

    @JsonProperty("id_tool")
    @NotNull(message = "Debe contener un id valido")
    private Long idTool;//Comprobar si es nulo o vacio!

    @NotNull(message = "La cantidad de alquiler es requerido")
    @NotBlank(message = "Ingrese un caracter valido")
    @Max(value = 2, message = "2 herramientas como maximo por producto")
    @Min(value = 1, message = "Agregue como minimo 1 herramienta")
    private int rentedQuantity;
}
