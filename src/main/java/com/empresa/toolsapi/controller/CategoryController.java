package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.category.CategoryDTO;
import com.empresa.toolsapi.dto.category.request.CategoryRequestDTO;
import com.empresa.toolsapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO){

        CategoryDTO newCategory = categoryService.createCategory(requestDTO);
        URI location = URI.create("/api/v1/category" + newCategory.getIdCategory());

        return ResponseEntity.created(location).body(newCategory);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> listCategory(){
        List<CategoryDTO> categoryDTOList = categoryService.listCategory();

        return ResponseEntity.ok(categoryDTOList);
    }

    @PutMapping("/update/{idCategory}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable Long idCategory,@RequestBody CategoryRequestDTO categoryRequestDTO){

        CategoryDTO updateCategory = categoryService.updateCategory(idCategory, categoryRequestDTO);

        return ResponseEntity.ok(updateCategory);

    }

    @DeleteMapping("delete/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long idCategory){

        categoryService.deleteCategory(idCategory);

        return ResponseEntity.noContent().build();
    }

}
