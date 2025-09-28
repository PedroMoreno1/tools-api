package com.empresa.toolsapi.service.impl;

import com.empresa.toolsapi.dto.tool.request.ToolRequestDTO;
import com.empresa.toolsapi.dto.tool.response.ToolResponseDTO;
import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;
import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.mapper.ToolMapper;
import com.empresa.toolsapi.repository.ToolRepository;
import com.empresa.toolsapi.utils.SectionCategory;
import com.empresa.toolsapi.validation.ToolValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//1 - Habilitar Mockito
@ExtendWith(MockitoExtension.class)
public class ToolServiceImplTest {
    //2 - Simular las dependencias (Mocks)
    @Mock
    private ToolRepository toolRepository;
    @Mock
    private ToolValidation toolValidation;

    //3 - Inyectar los mocks en la clase que se hara el test
    @InjectMocks
    private ToolServiceImpl toolService;

    @Test
    void testCreateTool(){
        //(1) -- Arrange (preparar los datos, mocks, comportamiento simulado)
        //(1.1)BASE DE DATOS
        // -Simulacion de los 'Section' y 'Category' existentes en la DB.
        Section sectionTest = new Section("scTest", "mi section test");
        Category categoryTest = new Category("ctTest");
        setId(sectionTest, "idSection", 1L); //Simula que la DB crea un id.
        setId(categoryTest, "idCategory", 1L); //Simula que la DB crea un id.

        //(1.2)REQUEST(DTO)
        // -Solicitud para crear herramienta
        ToolRequestDTO trDTOtest = new ToolRequestDTO();
        trDTOtest.setName("toolTest");
        trDTOtest.setDescription("mi primer test");
        trDTOtest.setImageUrl("https://img.com");
        trDTOtest.setIdSection(1L); //ID del sectionTest
        trDTOtest.setIdCategory(1L); //ID del categoryTest
        trDTOtest.setTotalQuantity(10);
        trDTOtest.setRentalPrice(BigDecimal.valueOf(45.50));

        //(1.3)VALIDAR-REQUEST
        // -Devuelve un Tipo 'SectionCategory' record, clase auxiliar         (<-- Paso 2: Retornar.)
        SectionCategory sc = new SectionCategory(sectionTest, categoryTest);
        // --> Comportamiento simulado (WHEN)

        // -Recibe la solicitud(extrae el 'idSection' y 'idCategory')         (<-- Paso 1: Realizar validacion.)
        // -Si pasa devuelve 'SectionCategory' tipo auxiliar -> (sc): contiene la entidad 'section' y 'category', sino lanza la excepcion correspondiente.
        when(toolValidation.validateSectionAndCategory(trDTOtest)).thenReturn(sc); //simulacion -> pasa

        //(1.4)CREAR HERRAMIENTA
        Tool newToolMock = ToolMapper.toEntity(trDTOtest, sc.section(), sc.category());

        setId(newToolMock, "idTool", 1L); //Simula que la DB crea un id.

        //(1.5)GUARDAR HERRAMIENTA
        // --> Comportamiento simulado (WHEN)
        when(toolRepository.save(any(Tool.class))).thenReturn(newToolMock); //simulacion -> pasa


        //(2) -- Act (llamar al metodo a testear)
        //--> seria como el controller
        ToolResponseDTO result = toolService.createTool(trDTOtest);

        //(3) -- Assert (verificar que el resultado es el esperado)
        assertNotNull(result); //que el resultado no sea nulo
        assertEquals(1, result.getId()); // verifica si el id es 1
        assertEquals(1, result.getSectionDTO().getIdSection());
        assertEquals("toolTest", result.getName()); //que "toolTest" sea el nombre de la tool
        assertEquals(10, result.getAvailableQuantity()); //que la cantidad total sea igual a la cantidad disponible
        assertEquals(BigDecimal.valueOf(45.50), result.getRentalPrice());

    }

    /**
     * Metodo para asignar id a campos privados sin setter.
     * -Maneja las excepciones.
     */
    private static void setId(Object entity, String fieldName, Long id){
        try{
            //import java.lang.reflect.Field.
            //Test con reflexion para asignar id a un campo privado sin setter.
            Field idField = entity.getClass().getDeclaredField(fieldName);
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException("Error al setear id en el test", e);
        }
    }
}
