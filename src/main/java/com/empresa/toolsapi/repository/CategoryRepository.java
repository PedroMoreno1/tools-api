package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
