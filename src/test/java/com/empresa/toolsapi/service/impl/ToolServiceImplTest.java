package com.empresa.toolsapi.service.impl;

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
import com.empresa.toolsapi.utils.ErrorMessages;
import com.empresa.toolsapi.utils.SectionCategory;
import com.empresa.toolsapi.validation.ToolValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    @Mock
    private TicketToolRepository ticketToolRepository;

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

    @Test
    void testUpdateTool(){

        //(1) -- Arrange (preparar los datos, mocks, comportamiento simulado)
        //(1.1)BASE DE DATOS
        // -Simulacion de los 'Section' y 'Category' existentes en la DB.
        Section sectionTest = new Section("scTest", "mi section test");
        Category categoryTest = new Category("ctTest");
        setId(sectionTest, "idSection", 1L); //Simula que la DB crea un id.
        setId(categoryTest, "idCategory", 1L); //Simula que la DB crea un id.

        Tool toolTest = new Tool();
        toolTest.setName("Serrucho");
        toolTest.setDescription("Herramienta nueva");
        toolTest.setImageUrl("http://link.com/img.jpg");
        toolTest.setSection(sectionTest);
        toolTest.setCategory(categoryTest);
        toolTest.setTotalQuantity(20);
        toolTest.setAvailableQuantity(20);
        toolTest.setRentalPrice(new BigDecimal("39.95"));
        toolTest.setCreatedAt(LocalDateTime.now().minusDays(2));
        toolTest.setUpdatedAt(LocalDateTime.now());
        toolTest.setActive(true);
        setId(toolTest, "idTool", 1L);

        //===

        //Request(Actualizacion parcial de solo 2 campos)
        ToolPatchDTO requestPatchTest = new ToolPatchDTO();
        requestPatchTest.setName("Serrucho C1");
        requestPatchTest.setUrlImg("http://link2.com/img22.jpg");

        //== When ==
        //Existe la herramienta con ese id? si, entonces devuelve el obj
        when(toolValidation.existsTool(1L)).thenReturn(toolTest);

        /**
         * Se envia solo los campos necesarios para actualizar(Patch), los que no se especifican
         * automaticamente pasan con null, entonces validamos, los que son null no pasan el if()
         * por eso no se setean.
         */
        when(toolValidation.isValidString(null)).thenReturn(false);
        //El nombre que reciba pasara
        when(toolValidation.isValidString(requestPatchTest.getName())).thenReturn(true);
        //La url que reciba pasara
        when(toolValidation.isValidString(requestPatchTest.getUrlImg())).thenReturn(true);


        //Guarda cualquier objeto de tipo tool, devuelve toolTest como resultado simulado
        when(toolRepository.save(any(Tool.class))).thenReturn(toolTest);

        //(2)ACT
        ToolResponseDTO response = toolService.updateToolPatch(1L, requestPatchTest);

        //(3)ASSERT
        assertNotNull(response);
        assertEquals("Serrucho C1", response.getName());
        //== Verify ==
        //existsTool fue invocado 1 vez con el id 1
        verify(toolValidation, times(1)).existsTool(1L);
        //isValidString fue invocado 3 veces con cualquier valor como argumento
        verify(toolValidation, times(3)).isValidString(any());


    }

    @Test
    void testDeactivateTool(){

        Section sectionTest = new Section("scTest", "mi section test");
        Category categoryTest = new Category("ctTest");
        setId(sectionTest, "idSection", 1L); //Simula que la DB crea un id.
        setId(categoryTest, "idCategory", 1L); //Simula que la DB crea un id.

        Tool toolTest = new Tool();
        toolTest.setName("Serrucho");
        toolTest.setDescription("Herramienta nueva");
        toolTest.setImageUrl("http://link.com/img.jpg");
        toolTest.setSection(sectionTest);
        toolTest.setCategory(categoryTest);
        toolTest.setTotalQuantity(20);
        toolTest.setAvailableQuantity(20);
        toolTest.setRentalPrice(new BigDecimal("39.95"));
        toolTest.setCreatedAt(LocalDateTime.now().minusDays(2));
        toolTest.setUpdatedAt(LocalDateTime.now());
        toolTest.setActive(true);
        setId(toolTest, "idTool", 1L);


        when(toolValidation.existsTool(1L)).thenReturn(toolTest);
        when(ticketToolRepository.existsByTool_IdToolAndTicket_IsActiveTrue(1L)).thenReturn(false); //No tiene ticket activo

        when(toolRepository.save(any(Tool.class))).thenReturn(toolTest);

        toolService.deactivateTool(1L);

        //El ticket cambio de estado a false?
        assertFalse(toolTest.isActive());


         /*

        when(toolValidation.existsTool(1L)).thenReturn(toolTest);
        when(ticketToolRepository.existsByTool_IdToolAndTicket_IsActiveTrue(1L)).thenReturn(true); // con esto("!") = false, No tiene tickets activos


        // Act + Assert
        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> toolService.deactivateTool(1L)
        );

        assertEquals(ErrorMessages.TOOL_TICKET_ACTIVE, exception.getMessage());

        // Verifica que NO se haya llamado al save, ya que se lanzó la excepción antes
        verify(toolRepository, never()).save(any());

         */
    }
}
