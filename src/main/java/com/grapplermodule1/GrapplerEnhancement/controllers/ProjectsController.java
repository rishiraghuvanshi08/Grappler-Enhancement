package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Team;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Project project) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletedById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Long id, @RequestBody Project project) {
        return null;
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<Team>> getAllTeams(@PathVariable Long id) {
        return null;
    }
    
    @GetMapping("/{id}/teams/{idTeam}")
    public ResponseEntity getTeamById(@PathVariable Long id, @PathVariable Long idTeam) {
        return null;
    }

    @PostMapping("/{id}/teams")
    public ResponseEntity create(@RequestBody Team team, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}/teams/{teamId}")
    public ResponseEntity deletedByIdPost(@PathVariable Long id, @PathVariable Long teamId) {
        return null;
    }

    @PutMapping("/{id}/teams/{teamId}")
    public ResponseEntity updateByTeamId(@PathVariable Long id, @PathVariable Long teamId, @RequestBody Team team) {
        return null;
    }
}
