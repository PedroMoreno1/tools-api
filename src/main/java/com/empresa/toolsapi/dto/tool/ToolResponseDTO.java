package com.empresa.toolsapi.dto.tool;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.section.SectionDTO;
import com.empresa.toolsapi.enums.ToolStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    private ToolStatus status;
    private String ticketCode;
}
