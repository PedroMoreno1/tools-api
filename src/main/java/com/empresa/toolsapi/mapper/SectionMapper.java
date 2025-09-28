package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.section.response.SectionResponseDTO;
import com.empresa.toolsapi.dto.section.request.SectionRequestDTO;
import com.empresa.toolsapi.entity.Section;

public class SectionMapper {

    public static Section toEntity(SectionRequestDTO requestDTO){

        return new Section(requestDTO.getName(), requestDTO.getDescription());
    }

    public static SectionResponseDTO toResponse(Section section){

        return SectionResponseDTO.builder()
                .idSection(section.getIdSection())
                .nameSection(section.getName())
                .description(section.getDescription())
                .build();
    }
}
