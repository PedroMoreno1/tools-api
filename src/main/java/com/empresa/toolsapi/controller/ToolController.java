package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.PaginatedResponseDTO;
import com.empresa.toolsapi.dto.tool.ToolPatchDTO;
import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;
import com.empresa.toolsapi.service.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/tool")
public class ToolController {

    private final ToolService toolService;

    @PostMapping("/create")
    public ResponseEntity<ToolResponseDTO> createTool(@Valid @RequestBody ToolRequestDTO dto){

        ToolResponseDTO newTool = toolService.createTool(dto);
        //Generar la URL del nuevo recurso
        URI location = URI.create("/api/v1/tool/"+ newTool.getId());

        return ResponseEntity.created(location).body(newTool);
    }

    @GetMapping("/list")
    public ResponseEntity<PaginatedResponseDTO<ToolResponseDTO>> toolList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size
    ){
        PaginatedResponseDTO<ToolResponseDTO> response = toolService.getAllTools(page, size);

      return ResponseEntity.ok(response);

        /*List<ToolResponseDTO> tools = toolService.getAllTools();

        return ResponseEntity.ok().body(tools);*/
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolResponseDTO> toolById(@PathVariable Long id){

        ToolResponseDTO tool = toolService.getToolById(id);

        return ResponseEntity.ok(tool);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ToolResponseDTO> updatePartialTool(@PathVariable Long id, @RequestBody ToolPatchDTO patchDTO){

        ToolResponseDTO patchTool = toolService.updateToolPatch(id, patchDTO);

        return ResponseEntity.ok(patchTool);
    }

    @PatchMapping("/updateQuantity/{id}")
    public ResponseEntity<ToolResponseDTO> updateQuantity(@PathVariable Long id, @RequestParam int amountToAdd){

        ToolResponseDTO response = toolService.updateTotalQuantity(id,amountToAdd);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<Void> deactivateTool(@PathVariable Long id){

        toolService.deactivateTool(id);

        return ResponseEntity.noContent().build();
    }
}
