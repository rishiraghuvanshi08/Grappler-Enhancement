package com.grapplermodule1.GrapplerEnhancement.repository;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
