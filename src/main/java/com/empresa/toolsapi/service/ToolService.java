package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.PaginatedResponseDTO;
import com.empresa.toolsapi.dto.tool.ToolPatchDTO;
import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;

public interface ToolService {
    ToolResponseDTO createTool(ToolRequestDTO dto);
    PaginatedResponseDTO<ToolResponseDTO> getAllTools(int page, int size);
    ToolResponseDTO getToolById(Long idTool);
    ToolResponseDTO updateToolPatch(Long idTool, ToolPatchDTO patchDTO);
    ToolResponseDTO updateTotalQuantity(Long idTool, int amountToAdd);
    void deactivateTool(Long idTool);


}
