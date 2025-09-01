package com.empresa.toolsapi.entity;

import com.empresa.toolsapi.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long idTicket;

    @Column(name = "ticket_code", unique = true, nullable = false)
    private String ticketCode;

    @OneToOne(optional = false)
    @JoinColumn(name = "tool_id", nullable = false)
    private Tool tool;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "borrowed_At", nullable = false, updatable = false)
    private LocalDateTime borrowedAt;
    @Column(name = "returned_At")
    private LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    //Para eliminación lógica
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;


    @OneToOne(mappedBy = "ticket")
    private ReturnDetails returnDetails;
}
