package com.empresa.toolsapi.dto.tool;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ToolPatchDTO {

    private String name;
    private String description;
    private String urlImg;
    private Long idSection;
    private Long idCategory;
}
