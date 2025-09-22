package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //List<Ticket> findByStatus(TicketStatus status);
    //boolean existsByToolAndAvailableQuantity(Tool tool, int availableQuantity);

    Optional<Ticket> findByTicketCode(String ticketCode);

}
