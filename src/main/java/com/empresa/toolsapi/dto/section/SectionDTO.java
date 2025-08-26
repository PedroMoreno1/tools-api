package com.empresa.toolsapi.dto.section;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SectionDTO {
    @JsonProperty("id")
    private Long idSection;
    private String nameSection;
}
