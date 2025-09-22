package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "return_details")
public class ReturnDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_details")
    private Long idDetails;

    @Column(length = 150)
    private String note;

    //Datos de la persona que realizara la devolucion
    @Column(name = "returned_by_name",length = 100)
    private String returnedByName;

    @Column(name = "returnedByDni", length = 8)
    private String returnedByDni;

    @OneToOne
    @JoinColumn(name = "ticket_id", unique = true)
    private Ticket ticket;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;
}
