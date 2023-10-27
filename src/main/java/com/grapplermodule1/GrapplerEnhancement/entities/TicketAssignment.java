package com.grapplermodule1.GrapplerEnhancement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grapplermodule1.GrapplerEnhancement.enums.PermissionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket_assignment")
public class TicketAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "assignment_id")
    private Long id;

    @Column(name = "permission_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Permission type is required")
    private PermissionType permission_type;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User Id is required")
    @JsonManagedReference
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