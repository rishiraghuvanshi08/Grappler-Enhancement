package com.grapplermodule1.GrapplerEnhancement.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hierarchy")
public class HierarchyController {

    @GetMapping("/reporting")
    public ResponseEntity getReportingHierarchy() {
        return null;
    }

    @GetMapping("/reporting/{userId}")
    public ResponseEntity getReportingHierarchy(@PathVariable("userId") Long userId) {
        return null;
    }

    @GetMapping("/team/{team_id}")
    public ResponseEntity getTeamHierarchy(@PathVariable Long team_id) {
        return null;
    }

}

