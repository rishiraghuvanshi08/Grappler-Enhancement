package com.grapplermodule1.GrapplerEnhancement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "permission_id")
    private Long id;

    @Column(name = "permission_type")
    private String permission_type;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private TeamMembers teamMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(String permission_type) {
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
