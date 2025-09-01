package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "delivered_by",length = 100)
    private String deliveredBy;
    @Column(name = "dni_person", length = 8)
    private String dniPerson;

    @OneToOne
    @JoinColumn(name = "id_ticket", unique = true)
    private Ticket ticket;
}
