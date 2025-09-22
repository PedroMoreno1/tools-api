package com.empresa.toolsapi.dto.tool.response;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.section.SectionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String urlImg;
    @JsonProperty("section")
    private SectionDTO sectionDTO;
    @JsonProperty("category")
    private CategoryDTO categoryDTO;
    private int totalQuantity;
    private int availableQuantity;
    private BigDecimal rentalPrice;
}
