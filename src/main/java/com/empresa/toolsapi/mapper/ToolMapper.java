package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.section.SectionDTO;
import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.entity.Tool;

public class ToolMapper {

    public static Tool toEntity(ToolRequestDTO dto, Section section, Category category){

        return Tool.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .section(section)
                .category(category)
                .totalQuantity(dto.getTotalQuantity())
                .availableQuantity(dto.getTotalQuantity()) //misma cantidad del total
                .rentalPrice(dto.getRentalPrice())
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
                .urlImg(tool.getImageUrl())
                .sectionDTO(sectionDTO)
                .categoryDTO(categoryDTO)
                .totalQuantity(tool.getTotalQuantity())
                .availableQuantity(tool.getAvailableQuantity())
                .rentalPrice(tool.getRentalPrice())
                .build();
    }
}
