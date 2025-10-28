package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Long idTicket;

    @Column(name = "ticket_code", unique = true, nullable = false)
    private String ticketCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "rented_date", nullable = false, updatable = false)
    private LocalDateTime rentedDate;
    @Column(name = "returned_date")
    private LocalDateTime returnedDate;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TicketTool> ticketTools = new ArrayList<>();

    @Column(name = "is_active")
    private boolean isActive = true;
}
