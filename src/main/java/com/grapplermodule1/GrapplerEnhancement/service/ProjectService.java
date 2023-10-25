package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.customexception.CustomResponse;
import com.grapplermodule1.GrapplerEnhancement.customexception.DataNotPresent;
import com.grapplermodule1.GrapplerEnhancement.customexception.DuplicateProjectName;
import com.grapplermodule1.GrapplerEnhancement.customexception.ProjectNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    /**Getting the list of users
     * @return List **/
    public List<Project> getAllProjects() {
        String debugUuid = UUID.randomUUID().toString();
        try {
            log.info("Getting List of all Projects");
            List<Project> projectList = projectRepository.findAll();
            if(!projectList.isEmpty()){
                log.info("Got List of all Projects that is present in db");
                return projectRepository.findAll();
            }
            else{
                log.info("Throws exception because there no project found with uuid", debugUuid);
                throw new ProjectNotFoundException("Project not Found");
            }
        }catch (Exception e) {
            log.info("Exception In Fetch All Projects Exception {}", e.getMessage());
            throw  e;
        }
    }
    /**
     * For Adding A New Project
     *
     * @return Project
     */
    public Project createProject(Project project) {
        String debugUuid = UUID.randomUUID().toString();
        try{
            if (projectRepository.existsByName(project.getName())) {
                log.error("Duplicate Project name in, UUID {}", debugUuid);
                throw new DuplicateProjectName("Project Name is already in use");
            }
            if (Objects.equals(project.getName(), "")){
                log.info("new Project detail is Empty, UUID {}", debugUuid);
                throw new DataNotPresent("Do not Pass Empty String");
            }
            log.info("Create new Project API Called, UUID {}", debugUuid);
            return projectRepository.save(project);
        }
        catch (Exception e){
            log.error("Exception In Add Project Service Exception {}", e.getMessage());
            throw e;
        }
    }
    /**
     * For getting A Project with id
     *
     * @return Project
     */
    public Project getProjectById(Long id) {
        String debugUuid = UUID.randomUUID().toString();
        try{
            log.info("Fetching Project with id : " + id);
            Optional<Project> project = projectRepository.findById(id);
            if (project.isPresent()){
                log.info("Project found with id : " + id);
                return project.get();
            }
            else {
                log.error("Throws exception because there no project found with uuid", debugUuid);
                throw new ProjectNotFoundException("Project not Found");
            }
        }catch (Exception e){
            log.error("Exception In Fetching Project with id, Exception {}", e.getMessage());
            throw  e;
        }
    }
    /**
     * For deleting A Project with id
     *
     * @return boolean
     */
    public boolean deletedByProjectId(Long projectId){
        String debugUuid = UUID.randomUUID().toString();
        try{
            log.info("Deleting project with id in service is called, project Id {}", projectId);
            Optional<Project> project = projectRepository.findById(projectId);
            if (project.isPresent()) {
                log.info("Deleting project with id successfully in service with uuid{}, ", debugUuid);
                projectRepository.deleteById(projectId);
                return true;
            } else {
                log.error("Throws exception because there no project found with uuid", debugUuid);
                throw new ProjectNotFoundException("Project not Found");
            }
        }catch (Exception e){
            log.error("Exception In Deleting Project with id, Exception {}", e.getMessage());
            throw  e;
        }

    }
    /**
     * For updating A Project with id
     *
     * @return Project
     */
    public Project updateProjectById(Long id,  Project project) {
        String debugUuid = UUID.randomUUID().toString();
        try{
            log.info("Inside Updating project with id in service with uuid{}, ", debugUuid);
            Project update = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Product not found"));
            update.setName(project.getName());
            if (projectRepository.existsByName(project.getName())) {
                log.error("Duplicate Project name in, UUID {}", debugUuid);
                throw new DuplicateProjectName("Project Name is already in use");
            }
            if (Objects.equals(project.getName(), "")){
                log.info("Update Project detail is Empty, UUID {}", debugUuid);
                throw new DataNotPresent("Do not Pass Empty String");
            }
            update.setTeams(project.getTeams());
            log.info("Updating project with id successfully in service with uuid{}, ", debugUuid);
            return projectRepository.save(update);
        }catch (Exception e){
            log.error("Exception In Deleting Project with id, Exception {}", e.getMessage());
            throw  e;
        }

    }

}
