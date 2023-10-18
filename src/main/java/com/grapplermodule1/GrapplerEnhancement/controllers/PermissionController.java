package com.grapplermodule1.GrapplerEnhancement.controllers;
import com.grapplermodule1.GrapplerEnhancement.entities.Permission;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    /**
     * For Getting Permissions Of Member In A Project
     *
     * @return ResponseEntity<Permission>
     */
    @GetMapping("/{memberId}/project-permission/{projectId}")
    public ResponseEntity<Permission> getMemberPermission
            (@PathVariable("memberId") Long memberId,
             @PathVariable("projectId") Long projectId) {
        return null;
    }

    /**
     * For Removing Permissions Of Member In A Project
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/{memberId}/remove-permissions/{projectId}")
    public ResponseEntity deleteMemberPermission(@PathVariable("memberId") Long memberId,
                                                 @PathVariable("projectId") Long projectId) {
        return null;
    }

    /**
     * For Adding Permissions Of Member In A Project
     *
     * @return ResponseEntity
     */
    @PostMapping("/{memberId}/add-permissions/{projectId}")
    public ResponseEntity addPermissionToMember(@PathVariable("memberId") Long memberId,
                                                @PathVariable("projectId") Long projectId) {
        return null;
    }
}
