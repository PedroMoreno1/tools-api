package com.empresa.toolsapi.entity;

import com.empresa.toolsapi.enums.ToolStatus;
import com.empresa.toolsapi.utils.AppSettings;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tool")
    private Long idTool;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Lob
    private String description;

    private String urlImg;

    //Con JoinColumn definimos el campo de la union que estara en esta tabla en la db, como su nombre, si es nulo, etc.
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    //Nuevo
    @Enumerated(EnumType.STRING)
    private ToolStatus status = ToolStatus.IN_STORE;

    @Column(name = "ticket_code", unique = true, nullable = false)
    private String ticketCode = AppSettings.NOT_TICKET;

}
