package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.section.SectionDTO;
import com.empresa.toolsapi.dto.tool.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.ToolResponseDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.enums.ToolStatus;
import com.empresa.toolsapi.utils.ErrorMessages;

public class ToolMapper {

    public static Tool toEntity(ToolRequestDTO dto, Section section, Category category){

        return Tool.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .urlImg(dto.getUrlImg())
                .section(section)
                .category(category)
                .status(ToolStatus.IN_STORE)
                .ticketCode(ErrorMessages.NOT_TICKET)
                .build();
    }

    public static ToolResponseDTO toResponseDTO(Tool tool){

        SectionDTO sectionDTO = SectionDTO.builder()
                .idSection(tool.getSection().getIdSection())
                .nameSection(tool.getSection().getName()).
                build();
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .idCategory(tool.getCategory().getIdCategory())
                .nameCategory(tool.getCategory().getName())
                .build();

        return ToolResponseDTO.builder()
                .id(tool.getIdTool())
                .name(tool.getName())
                .description(tool.getDescription())
                .urlImg(tool.getUrlImg())
                .sectionDTO(sectionDTO)
                .categoryDTO(categoryDTO)
                .status(tool.getStatus())
                .ticketCode(tool.getTicketCode())
                .build();
    }
}
