package com.empresa.toolsapi.repository;

import com.empresa.toolsapi.entity.ReturnDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReturnDetailsRepository extends JpaRepository<ReturnDetails, Long> {

    //Dentro de la entidad ReturnDetails busca si existe un registro con el id del ticket consultado.
    //ReturnDetails -> Ticket(foranea) -> idTicket(asi se llama el id en la entidad ticket).
    boolean existsByTicket_IdTicket(Long idTicket);
}
