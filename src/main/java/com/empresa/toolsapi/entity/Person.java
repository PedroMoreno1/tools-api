package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Person")
    private Long idPerson;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(nullable = false
            , length = 100)
    private String name;
}
