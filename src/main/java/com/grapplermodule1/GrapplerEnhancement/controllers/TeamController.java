package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    /**
     * For Getting Team Data
     *
     * @return ResponseEntity<List<Team>>
     */
    @GetMapping("/")
    public ResponseEntity<List<Team>> getAllTeams(){
        return null;
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamId") Long teamId){
        return null;
    }

    @PostMapping("/")
    public ResponseEntity addNewTeam(@RequestBody Team team){
        return null;
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity deleteTeamById(@PathVariable("teamId") Long teamId){
        return null;
    }

    @PutMapping("/{teamId}")
    public ResponseEntity updateTeamById(@PathVariable("teamId") Long teamId,@RequestBody Team team){
        return null;
    }


}
