package com.empresa.toolsapi.service;

import com.empresa.toolsapi.dto.section.response.SectionResponseDTO;
import com.empresa.toolsapi.dto.section.request.SectionRequestDTO;

import java.util.List;

public interface SectionService {

    SectionResponseDTO createSection(SectionRequestDTO requestDTO);
    List<SectionResponseDTO> listSection();
    SectionResponseDTO updateSection(Long idSection, SectionRequestDTO requestDTO);
    void deleteSection(Long idSection);
}
