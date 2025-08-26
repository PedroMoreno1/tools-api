package com.empresa.toolsapi.utils;

import com.empresa.toolsapi.entity.Category;
import com.empresa.toolsapi.entity.Section;

/**
 * Gracias a Java 16+ puedo crear una clase auxiliar, me pasaba que queria
 * usar este metodo: validateSectionAndCategory() para validar pero no me devolvia
 * el section, ni el category para armar luego el dto, entonces llegue a esto.
 *
 * Forma compacta para declarar una clase que solo contiene datos(POJO/DTO)
 * Genera automaticamente: constructor, getter, equals(), hashcode(), y toString().
 * @param section
 * @param category
 */
public record SectionCategory(Section section, Category category) {
}
