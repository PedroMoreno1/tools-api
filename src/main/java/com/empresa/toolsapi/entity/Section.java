package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_section")
    private Long idSection;

    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<Tool> tools = new ArrayList<>();

    public Section(String name, String description){
        this.name = name;
        this.description = description;
    }

}
