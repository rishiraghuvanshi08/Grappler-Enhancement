package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Permission;
import com.grapplermodule1.GrapplerEnhancement.entities.TeamMembers;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team-members")
public class TeamMemberController {

    @GetMapping("/{teamId}")
    public ResponseEntity<List<Users>> getTeamMembers(@PathVariable("teamId") Long teamId){
        return null;
    }

    @GetMapping("/{teamId}/member/{memberId}")
    public ResponseEntity<Users> getTeamMembers(@PathVariable("teamId") Long teamId, @PathVariable("memberId") Long memberId){
        return null;
    }

    @PostMapping("/{teamId}/add-new-member/{userId}")
    public ResponseEntity addNewMember(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId){
        return null;
    }

    @DeleteMapping("/{teamId}/delete-member/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("teamId") Long teamId, @PathVariable("memberId") Long memberId){
        return null;
    }

}
