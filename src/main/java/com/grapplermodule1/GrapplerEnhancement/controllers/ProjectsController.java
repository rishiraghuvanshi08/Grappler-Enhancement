package com.grapplermodule1.GrapplerEnhancement.controllers;

import com.grapplermodule1.GrapplerEnhancement.customException.ProjectNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.entities.Team;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import com.grapplermodule1.GrapplerEnhancement.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    private static final Logger log = LoggerFactory.getLogger(ProjectsController.class);

    /**For Getting List of Projects
    @return ResponseEntity**/
    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        String debugUuid = UUID.randomUUID().toString();

        List<Project> projectList = projectService.getAllProjects();
        try {
            log.info("Getting data of all Projects");
            if (!projectList.isEmpty()){
                log.info("Get all Project info if there is any, UUID {}", debugUuid);
                return new ResponseEntity<>(projectList, HttpStatus.OK);
            }
            else {
                log.info("Project info is not present, UUID {}", debugUuid);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(ProjectNotFoundException p){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            log.info("Getting exception when we are trying to fetch the list of the projects, UUID {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addProject")
    public ResponseEntity<?> create(@RequestBody Project project) {
        String debugUuid = UUID.randomUUID().toString();
        Optional<Project> addProject = Optional.ofNullable(projectService.createProject(project));
        try {
            log.info("Add new project and their details with uuid{}", debugUuid);
            if (addProject.isPresent()){
                log.info("Add new project and their details if project details is present with uuid{}", debugUuid);
                return new ResponseEntity<>(addProject, HttpStatus.OK);
            }
            else {
                log.info("Project was not added because project details is not present uuid{}", debugUuid);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            log.info("Getting exception while adding a new project with UUID {}", debugUuid, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable Long projectId) {
        String debugUuid = UUID.randomUUID().toString();
        log.info("Inside Get User By Id,UUID {} ", projectId);
        Optional<Project> project = Optional.ofNullable(projectService.getProjectById(projectId));
        try{
            log.info("Get project By Id API Called, UUID {}", debugUuid);
            if (project.isPresent()){
                return new ResponseEntity<>(project, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity deletedById(@PathVariable Long projectId) {
        boolean project = projectService.deletedByProjectId(projectId);
        try{
            if (project){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PutMapping("/{projectId}")
//    public ResponseEntity updateById(@PathVariable Long projectId, @RequestBody Project project) {
//        Optional<Project> is= projectService.updateProjectById(projectId, project);
//        try{
//
//        }
//    }

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
