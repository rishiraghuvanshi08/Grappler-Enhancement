package com.grapplermodule1.GrapplerEnhancement.repository;

import com.grapplermodule1.GrapplerEnhancement.dtos.TeamDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TeamDTO(t.id, t.name) FROM Team t")
    List<TeamDTO> findListOfTeams();

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TeamDTO(t.id, t.name) FROM Team t WHERE t.id = :teamId")
    Optional<TeamDTO> findTeamById(Long teamId);

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TeamDTO(t.id, t.name) FROM Project p JOIN p.teams t WHERE p.id = :projectId")
    List<TeamDTO> searchTeamById(Long projectId);

    Optional<Team> findByName(String name);

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.TeamDTO(t.id, t.name) FROM Team t " +
            "INNER JOIN t.teamMembers tm " +
            "INNER JOIN tm.user u " +
            "WHERE u.id = :userId")
    Optional<List<TeamDTO>> findTeamsByUserId(Long userId);


}
