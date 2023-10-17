package com.grapplermodule1.GrapplerEnhancement.entity;

import com.grapplermodule1.GrapplerEnhancement.enums.PermissionType;
import jakarta.persistence.*;

@Entity
@Table(name = "TicketAssignment")
public class TicketAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="permission_type")
    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

}
