package com.empresa.toolsapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "ticket_tool")
public class TicketTool {

    @EmbeddedId //clave embebida
    private TicketToolPK id;

    @ManyToOne
    @MapsId("ticketId")//Indica parte de la clave embebida
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @MapsId("toolId")
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "rental_cost_unit", precision = 10, scale = 2, nullable = false)
    private BigDecimal rentalCostUnit;

    @Column(name = "total_cost_rent", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalCostRent;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;

    public TicketTool(Ticket ticket, Tool tool){
        this.ticket = ticket;
        this.tool = tool;

        this.id = new TicketToolPK(ticket.getIdTicket(), tool.getIdTool());
    }
}
