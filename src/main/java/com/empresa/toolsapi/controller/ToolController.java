package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.PaginatedResponseDTO;
import com.empresa.toolsapi.dto.tool.ToolPatchDTO;
import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;
import com.empresa.toolsapi.service.ToolService;
import com.empresa.toolsapi.utils.AppSettings;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<ToolResponseDTO> updateTool(@Valid @PathVariable Long id, @RequestBody ToolRequestDTO dto){

        ToolResponseDTO updateTool = toolService.updateTool(dto,id);

        return ResponseEntity.ok(updateTool);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteToolById(@PathVariable Long id){

        toolService.deleteTool(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ToolResponseDTO> updatePartialTool(@PathVariable Long id, @RequestBody ToolPatchDTO patchDTO){

        ToolResponseDTO patchTool = toolService.updateToolPatch(id, patchDTO);

        return ResponseEntity.ok(patchTool);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ToolResponseDTO>> searchTools(@RequestParam String idTool){

        List<ToolResponseDTO> listTools = toolService.searchTools(idTool);

        return ResponseEntity.ok(listTools);
    }
}
