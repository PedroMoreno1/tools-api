package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Long> {

}
