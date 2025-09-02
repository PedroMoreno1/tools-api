package com.empresa.toolsapi.validation;

import com.empresa.toolsapi.dto.tool.ToolRequestDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.exception.BadRequestException;
import com.empresa.toolsapi.exception.ResourceNotFoundException;
import com.empresa.toolsapi.repository.CategoryRepository;
import com.empresa.toolsapi.repository.SectionRepository;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.utils.ErrorMessages;
import com.empresa.toolsapi.utils.SectionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToolValidation {

    private final ToolRepository toolRepository;
    private final SectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;

    //"SectionCategory"-Tipo de objeto(una clase especial) que define un tipo de dato compuesto.
    //Sirve para transportar datos, dos o mas objetos relacionados como un solo paquete.

    //Verifica si seccion y categoria tienen id valido y existen
    public SectionCategory validateSectionAndCategory(ToolRequestDTO dto){

        if (dto.getIdSection() == null){
            throw new BadRequestException(ErrorMessages.SECTION_ID_NOTNULL);
        }
        if (dto.getIdCategory() == null){
            throw new BadRequestException(ErrorMessages.CATEGORY_ID_NOTNULL);
        }

        Section section = sectionRepository.findById(dto.getIdSection())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.SECTION_NOT_FOUND));

        Category category = categoryRepository.findById(dto.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND));

        return new SectionCategory(section, category);
    }

    //Verifica si la herramienta existe
    public Tool existsTool(Long idTool){

        //Al usar : findById <- nos devuelve el objeto, si usamos existsById <- nos devuelve boolean.
        return toolRepository.findById(idTool)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorMessages.TOOL_NOT_FOUND));
    }

    //Verifica SI seccion existe
    public Section sectionValid(Long idSection){
        return sectionRepository.findById(idSection)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorMessages.SECTION_NOT_FOUND));
    }

    //Verifica SI categoria existe
    public Category categoryValid(Long idSection){
        return categoryRepository.findById(idSection)
                .orElseThrow(()-> new ResourceNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND));
    }

    //Para validar si el valor es nulo, no tener campos asi: ("") o (" ") con espacios en blanco.
    public boolean isValidString(String value) {
        //El valor que entra NO es nulo y eliminando los espacios en blanco con (.trim()) tiene caracteres o queda algo valido.
        return value != null && !value.trim().isEmpty();
    }

    //Verifica si es id existe(Tipo: boolean)
    public void idExists(Long idTool){
        if (!toolRepository.existsById(idTool)){
            // String.format permite crear cadenas con variables insertadas de forma limpia y legible, usando placeholders como %d para números, %s para texto, etc.
            throw new ResourceNotFoundException(String.format(ErrorMessages.TOOL_ID_NOT_EXISTS, idTool));
        }
    }
}
