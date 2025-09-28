package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.category.request.CategoryRequestDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.mapper.CategoryMapper;
import com.empresa.toolsapi.repository.CategoryRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.service.CategoryService;
import com.empresa.toolsapi.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ToolRepository toolRepository;

    @Override
    public CategoryDTO createCategory(CategoryRequestDTO requestDTO) {

        Category category = CategoryMapper.toEntity(requestDTO);
        categoryRepository.save(category);

        return CategoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryDTO> listCategory() {

        List<Category> list = categoryRepository.findAll();

        return list.stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryDTO updateCategory(Long idCategory, CategoryRequestDTO requestDTO) {

        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND));

        category.setName(requestDTO.getName());
        Category updateCategory = categoryRepository.save(category);

        return CategoryMapper.toResponse(updateCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {

        if(!categoryRepository.existsById(idCategory)){
            throw new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }

        if(toolRepository.existsByCategory_IdCategory(idCategory)){
            throw new BadRequestException("Esta categoria tiene herramientas asociadas");
        }

        categoryRepository.deleteById(idCategory);

    }
}
