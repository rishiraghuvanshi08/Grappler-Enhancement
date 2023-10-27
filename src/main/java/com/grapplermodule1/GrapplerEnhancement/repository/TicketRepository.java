package com.grapplermodule1.GrapplerEnhancement.repository;

import com.grapplermodule1.GrapplerEnhancement.dtos.TicketsDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TicketsDTO(t.id, t.name,t.project.id,t.user.id) FROM Ticket t WHERE t.project.id = :projectId")
    Optional<List<TicketsDTO>> findByProjectId(Long projectId);

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TicketsDTO(ta.ticket.id, ta.ticket.name,ta.ticket.project.id,ta.ticket.user.id) FROM TicketAssignment ta WHERE ta.user.id = :userId")
    Optional<List<TicketsDTO>> findByUserId(Long userId);



}



