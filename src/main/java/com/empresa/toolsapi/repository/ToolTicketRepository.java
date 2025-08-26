package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.Tool;
import com.empresa.toolsapi.entity.Ticket;
import com.empresa.toolsapi.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToolTicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(TicketStatus status);
    boolean existsByToolAndStatus(Tool tool, TicketStatus status);
    Optional<Ticket> findByTicketCode(String ticketCode);
}
