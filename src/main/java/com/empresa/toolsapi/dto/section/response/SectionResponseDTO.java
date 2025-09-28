package com.empresa.toolsapi.dto.section.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class SectionResponseDTO {

    private Long idSection;
    private String nameSection;
    private String description;
}
