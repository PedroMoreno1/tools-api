package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.section.response.SectionResponseDTO;
import com.empresa.toolsapi.dto.section.request.SectionRequestDTO;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.mapper.SectionMapper;
import com.empresa.toolsapi.repository.SectionRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.service.SectionService;
import com.empresa.toolsapi.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ToolRepository toolRepository;

    @Override
    public SectionResponseDTO createSection(SectionRequestDTO requestDTO) {

        Section section = SectionMapper.toEntity(requestDTO);

        return SectionMapper.toResponse(sectionRepository.save(section));
    }

    @Override
    public List<SectionResponseDTO> listSection() {

        List<Section> listSection = sectionRepository.findAll();

        return listSection.stream()
                .map(SectionMapper::toResponse)
                .toList();
    }

    @Override
    public SectionResponseDTO updateSection(Long idSection, SectionRequestDTO requestDTO) {

        Section section = sectionRepository.findById(idSection)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.SECTION_NOT_FOUND));

        section.setName(requestDTO.getName());
        section.setDescription(requestDTO.getDescription());

        Section updateSection = sectionRepository.save(section);

        return SectionMapper.toResponse(updateSection);
    }

    @Override
    public void deleteSection(Long idSection) {

        if (!sectionRepository.existsById(idSection)){
            throw new ResourceNotFoundException(ErrorMessages.SECTION_NOT_FOUND);
        }
        if (toolRepository.existsBySection_IdSection(idSection)){
            throw new BadRequestException("Esta seccion tiene herramientas asociadas");
        }

        sectionRepository.deleteById(idSection);

    }
}
