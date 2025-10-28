package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.TicketTool;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketToolRepository extends JpaRepository<TicketTool, Long> {

    /**
     * TicketTool -> Buscar si hay idTool -> (Long idTool)
     * -> buscar su Ticket relacionado -> Si el ticket isActive = true -> pasa.
     *
     * @param idTool
     * @return
     */
    boolean existsByTool_IdToolAndTicket_IsActiveTrue(Long idTool);
}
