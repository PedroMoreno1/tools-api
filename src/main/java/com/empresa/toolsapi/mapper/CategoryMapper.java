package com.empresa.toolsapi.mapper;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.category.request.CategoryRequestDTO;
import com.empresa.toolsapi.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO requestDTO){

        return new Category(requestDTO.getName());
    }

    public static CategoryDTO toResponse (Category category){

        return CategoryDTO.builder()
                .idCategory(category.getIdCategory())
                .nameCategory(category.getName())
                .build();
    }
}
