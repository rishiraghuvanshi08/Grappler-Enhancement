package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.controllers.ProjectsController;
import com.grapplermodule1.GrapplerEnhancement.customException.ProjectNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import com.grapplermodule1.GrapplerEnhancement.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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
    }
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElse(null);
    }
    public boolean deletedByProjectId(Long id){
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Project updateProjectById(Long id,  Project project) {
        Project update = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        update.setName(project.getName());
        update.setTeams(project.getTeams());
        return projectRepository.save(update);
    }

}
