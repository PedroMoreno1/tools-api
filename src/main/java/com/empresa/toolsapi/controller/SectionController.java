package com.empresa.toolsapi.controller;

import com.empresa.toolsapi.dto.section.response.SectionResponseDTO;
import com.empresa.toolsapi.dto.section.request.SectionRequestDTO;
import com.empresa.toolsapi.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/section")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/create")
    public ResponseEntity<SectionResponseDTO> createSection(@Valid @RequestBody SectionRequestDTO requestDTO){

        SectionResponseDTO newSection = sectionService.createSection(requestDTO);
        URI location = URI.create("/api/v1/section/" + newSection.getIdSection());

        return ResponseEntity.created(location).body(newSection);
    }

    @GetMapping("/list")
    public ResponseEntity<List<SectionResponseDTO>> listSection(){

        List<SectionResponseDTO> sectionList = sectionService.listSection();

        return ResponseEntity.ok(sectionList);
    }

    @PutMapping("/update/{idSection}")
    public ResponseEntity<SectionResponseDTO> updateSection(@Valid @PathVariable Long idSection, @RequestBody SectionRequestDTO requestDTO){

        SectionResponseDTO updateSection = sectionService.updateSection(idSection, requestDTO);

        return ResponseEntity.ok(updateSection);

    }

    @DeleteMapping("/delete/{idSection}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long idSection){

        sectionService.deleteSection(idSection);

        return ResponseEntity.noContent().build();
    }

}
