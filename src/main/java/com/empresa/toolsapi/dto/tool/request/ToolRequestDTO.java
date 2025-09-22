package com.empresa.toolsapi.dto.tool.request;

import com.empresa.toolsapi.utils.ErrorMessages;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Getter
@Setter
public class ToolRequestDTO {

    @NotBlank(message = ErrorMessages.NAME_REQUIRED)
    @Size(max = 100, message = ErrorMessages.NAME_SIZE)
    private String name;
    private String description;
    @NotBlank(message = ErrorMessages.IMG_REQUIRED)
    @URL(message = ErrorMessages.IMG_URL_INVALID)
    private String imageUrl;

    @NotNull(message = ErrorMessages.SECTION_ID_NOTNULL)
    private Long idSection;
    @NotNull(message = ErrorMessages.CATEGORY_ID_NOTNULL)
    private Long idCategory;

    @NotNull(message = ErrorMessages.TOTAL_QUANTITY_REQUIRED)
    @Min(value = 0, message = ErrorMessages.TOTAL_QUANTITY_MIN)
    @Max(value = 2500, message = ErrorMessages.TOTAL_QUANTITY_MAX)
    private int totalQuantity;

    @DecimalMin(value = "0.0", message = ErrorMessages.RENTAL_PRICE_MIN)
    @DecimalMax(value = "9999.99", message = ErrorMessages.RENTAL_PRICE_MAX)
    private BigDecimal rentalPrice;
}
