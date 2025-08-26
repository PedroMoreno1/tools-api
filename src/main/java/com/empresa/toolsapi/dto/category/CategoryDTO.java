package com.empresa.toolsapi.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CategoryDTO {

    @JsonProperty("id")
    private Long idCategory;
    private String nameCategory;
}
