package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import com.grapplermodule1.GrapplerEnhancement.service.HierarchyService;
import com.grapplermodule1.GrapplerEnhancement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @GetMapping("/reporting/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Users getReportingHierarchy(@PathVariable("id") Long id) {
        return null;
    }

//    @GetMapping("/reporting/{userId}")
//    public ResponseEntity getReportingHierarchyById(@PathVariable("userId") Long userId) {
//        return null;
//    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity getTeamHierarchy(@PathVariable("teamId") Long teamId) {
        return null;
    }

}

