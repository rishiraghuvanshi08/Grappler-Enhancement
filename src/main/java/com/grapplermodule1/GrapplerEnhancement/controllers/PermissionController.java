package com.grapplermodule1.GrapplerEnhancement.controllers;
import com.grapplermodule1.GrapplerEnhancement.entities.Permission;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @GetMapping("/{memberId}/project-permission/{projectId}")
    public ResponseEntity<Permission> getMemberPermission
            (@PathVariable("memberId") Long memberId,
             @PathVariable("projectId") Long projectId) {
        return null;
    }

    @DeleteMapping("/{memberId}/remove-permissions/{projectId}")
    public ResponseEntity deleteMemberPermission(@PathVariable("memberId") Long memberId,
                                                 @PathVariable("projectId") Long projectId) {
        return null;
    }

    @PostMapping("/{memberId}/add-permissions/{projectId}")
    public ResponseEntity addPermissionToMember(@PathVariable("memberId") Long memberId,
                                                @PathVariable("projectId") Long projectId) {
        return null;
    }
}
