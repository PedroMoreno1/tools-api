package com.empresa.toolsapi.utils;

public class ErrorMessages {

    public static final String TOOL_NOT_AVAILABLE = "Herramienta no disponible - alquilada";
    public static final String TOOL_NOT_FOUND = "Herramienta no encontrada";
    public static final String TOOL_ID_NOT_EXISTS = "Herramienta con ID: %d no existe";

    public static final String PERSON_NOT_FOUND = "Persona no encontrada";
    public static final String DNI_UNREGISTERED = "Dni no registrado";

    public static final String SECTION_NOT_FOUND = "Sección no encontrada";
    public static final String CATEGORY_NOT_FOUND = "Categoria no encontrada";

    public static final String SECTION_ID_NOTNULL = "El ID de SECTION no puede ser nulo";
    public static final String CATEGORY_ID_NOTNULL = "El ID de CATEGORY no puede ser nulo";

    public static final String TICKET_CODE_NOT_EXISTS = "El código de ticket: %s no existe";
    public static final String TICKET_NOT_FOUND = "Ticket no encontrado";
    public static final String TICKET_CLOSED = "Ticket no disponible - cerrado";
    public static final String QUANTITY_NOT_AVAILABLE = "Cantidad seleccionada no disponible";

    //VALIDATIONS DTO
    public static final String NAME_REQUIRED = "El nombre es requerido";
    public static final String NAME_SIZE = "El nombre debe tener como máximo 100 caracteres";

    public static final String IMG_REQUIRED = "La URL de imagen es requerida";
    public static final String IMG_URL_INVALID = "Debe contener una URL valida";

    public static final String TOTAL_QUANTITY_REQUIRED = "La cantidad total es requerida";
    public static final String TOTAL_QUANTITY_MIN = "La cantidad total no debe ser menor a 0";
    public static final String TOTAL_QUANTITY_MAX = "La cantidad total no debe ser mayor a 2500";

    public static final String RENTAL_PRICE_MIN = "El precio del alquiler no debe ser menor a 0";
    public static final String RENTAL_PRICE_MAX = "Precio demasiado alto, no exceder de '9999.99'";


    //STATUS
    public static final String TOOL_TICKET_ACTIVE = "Herramienta con ticket activo";

    //QUANTITY
    public static final String TOOL_QUANTITY_RANGE = "La cantidad agregada debe ser de min 1 y max 10";

}
