package com.grapplermodule1.GrapplerEnhancement.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "team_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<TeamMembers> teamMembers;

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

    public List<TeamMembers> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<TeamMembers> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
