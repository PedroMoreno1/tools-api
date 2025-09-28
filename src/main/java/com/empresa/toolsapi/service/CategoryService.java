package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.category.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryRequestDTO requestDTO);
    List<CategoryDTO> listCategory();
    CategoryDTO updateCategory(Long idCategory, CategoryRequestDTO requestDTO);
    void deleteCategory(Long idCategory);


}
