package com.grapplermodule1.GrapplerEnhancement.repository;
import com.grapplermodule1.GrapplerEnhancement.dtos.ProjectDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.ProjectDTO(p.id, p.name) FROM Project p")
    List<ProjectDTO> findListOfProjects();

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.ProjectDTO(p.id, p.name) FROM Project p WHERE p.id = :projectId")
    Optional<ProjectDTO> findProjectById(Long projectId);



}
