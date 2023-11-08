package com.grapplermodule1.GrapplerEnhancement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ticket_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Ticket name is required")
    private String name;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;


    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "creator_id")
    private Users user;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private List<TicketAssignment> ticketAssignment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}