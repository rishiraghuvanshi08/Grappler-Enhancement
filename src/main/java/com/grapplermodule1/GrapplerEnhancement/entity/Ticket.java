package com.grapplermodule1.GrapplerEnhancement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "name")
    private String name;



}
