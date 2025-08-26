package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.PaginatedResponseDTO;
import com.empresa.toolsapi.dto.tool.ToolPatchDTO;
import com.empresa.toolsapi.dto.tool.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.ToolResponseDTO;

import java.util.List;

public interface ToolService {
    ToolResponseDTO createTool(ToolRequestDTO dto);
    PaginatedResponseDTO<ToolResponseDTO> getAllTools(int page, int size);
    ToolResponseDTO getToolById(Long idTool);
    ToolResponseDTO updateTool(ToolRequestDTO dto, Long idTool);
    ToolResponseDTO updateToolPatch(Long idTool, ToolPatchDTO patchDTO);
    void deleteTool(Long idTool);

    List<ToolResponseDTO> searchTools(String idTool);

}
