package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.PaginatedResponseDTO;
import com.empresa.toolsapi.dto.tool.ToolPatchDTO;
import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.mapper.ToolMapper;
import com.empresa.toolsapi.repository.TicketToolRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.service.ToolService;
import com.empresa.toolsapi.utils.ErrorMessages;
import com.empresa.toolsapi.utils.SectionCategory;
import com.empresa.toolsapi.validation.ToolValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;
    private final ToolValidation toolValidation;
    private final TicketToolRepository ticketToolRepository;

    @Override
    public ToolResponseDTO createTool(ToolRequestDTO dto) {

        SectionCategory sc = toolValidation.validateSectionAndCategory(dto);
        Tool newTool = ToolMapper.toEntity(dto, sc.section(), sc.category());
        Tool saved = toolRepository.save(newTool);

        return ToolMapper.toResponseDTO(saved);
    }

    @Override
    public PaginatedResponseDTO<ToolResponseDTO> getAllTools(int page, int size) {

        //Usa su pripio metodo .map()
        //permite transformar los elementos de la página
        // sin perder la metadata (como total de elementos, número de página, etc.)

        Page<ToolResponseDTO> pageResult = toolRepository.findAll(PageRequest.of(page, size))
                .map(ToolMapper::toResponseDTO);

        return new PaginatedResponseDTO<>(pageResult);

        /*return toolRepository.findAll().stream()
                .map(ToolMapper::toResponseDTO)
                .collect(Collectors.toList());*/
    }

    @Override
    public ToolResponseDTO getToolById(Long idTool) {

        Tool existsTool = toolValidation.existsTool(idTool);
        return ToolMapper.toResponseDTO(existsTool);
    }

    @Override
    public ToolResponseDTO updateToolPatch(Long idTool, ToolPatchDTO patchDTO) {

        Tool existsTool = toolValidation.existsTool(idTool);

        if (toolValidation.isValidString(patchDTO.getName())) {
            existsTool.setName(patchDTO.getName().trim()); // Guardás el valor limpio, sin espacios.
        }

        if (toolValidation.isValidString(patchDTO.getDescription())) {
            existsTool.setDescription(patchDTO.getDescription().trim());
        }

        if (toolValidation.isValidString(patchDTO.getUrlImg())) {
            existsTool.setImageUrl(patchDTO.getUrlImg().trim());
        }


        if(patchDTO.getIdSection() != null && !patchDTO.getIdSection().equals(existsTool.getSection().getIdSection())){

            Section existsSection = toolValidation.sectionValid(patchDTO.getIdSection());
            existsTool.setSection(existsSection);
        }

        // Si patchDTO tiene un idCategory NO nulo
        // y ese idCategory es diferente al de la categoría actual de la herramienta
        if(patchDTO.getIdCategory() != null && !patchDTO.getIdCategory().equals(existsTool.getCategory().getIdCategory())){

            Category existsCategory = toolValidation.categoryValid(patchDTO.getIdCategory());
            existsTool.setCategory(existsCategory);
        }

        toolRepository.save(existsTool);
        return ToolMapper.toResponseDTO(existsTool);

    }

    /**
     * Actualizar la cantidad total de una herramienta en especifico.
     * @param idTool
     * @param amountToAdd
     * @return
     */
    @Override
    public ToolResponseDTO updateTotalQuantity(Long idTool, int amountToAdd) {

        Tool tool = toolValidation.existsTool(idTool);

        int total = tool.getTotalQuantity();
        int available = tool.getAvailableQuantity();

        toolValidation.amountRange(amountToAdd);

        tool.setTotalQuantity(total + amountToAdd);
        tool.setAvailableQuantity(available + amountToAdd);

        Tool updateQuantity = toolRepository.save(tool);

        return ToolMapper.toResponseDTO(updateQuantity);
    }

    /**
     * Desactiva la herramienta si no tiene un ticket activo
     * @param idTool
     */
    @Override
    public void deactivateTool(Long idTool) {

        Tool tool = toolValidation.existsTool(idTool);

        if (ticketToolRepository.existsByTool_IdToolAndTicket_IsActiveTrue(tool.getIdTool())){
            throw new BadRequestException(ErrorMessages.TOOL_TICKET_ACTIVE);
        }

        tool.setActive(false);

        toolRepository.save(tool);
    }
}
