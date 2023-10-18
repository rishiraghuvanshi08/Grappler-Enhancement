package com.grapplermodule1.GrapplerEnhancement.entities;

import com.grapplermodule1.GrapplerEnhancement.enums.PermissionType;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket_assignment")
public class TicketAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "assignment_id")
    private Long id;

    @Column(name = "permission_type")
    @Enumerated(EnumType.STRING)
    private PermissionType permission_type;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionType getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(PermissionType permission_type) {
        this.permission_type = permission_type;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}