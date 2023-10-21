package com.grapplermodule1.GrapplerEnhancement.entities;

import com.grapplermodule1.GrapplerEnhancement.enums.AccessLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "permission_id")
    private Long id;

    @Column(name = "permission_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Permission type is required")
    private AccessLevel permission_type;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @NotNull(message = "Project is required")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @NotNull(message = "Team member is required")
    private TeamMembers teamMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccessLevel getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(AccessLevel permission_type) {
        this.permission_type = permission_type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TeamMembers getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(TeamMembers teamMembers) {
        this.teamMembers = teamMembers;
    }
}
