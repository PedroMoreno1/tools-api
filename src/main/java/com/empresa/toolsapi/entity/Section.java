package com.empresa.toolsapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private Long idSection;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
    //Lob porque se espera una descripcion bien detallada lo cual puede ser mucho texto.
    @Lob
    private String description;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<Tool> tools = new ArrayList<>();

}
