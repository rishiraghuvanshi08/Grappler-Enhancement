package com.grapplermodule1.GrapplerEnhancement.repository;

import com.grapplermodule1.GrapplerEnhancement.dtos.HierarchyDTO;
import com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByDesignation(String designation);

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO(e.id, e.name, e.email, e.designation) " +
            "FROM Users e")
    Optional<List<UsersDTO>> findAllUser();

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO(e.id, e.name, e.email, e.designation) " +
            "FROM Users e WHERE e.id = :userId")
    Optional<UsersDTO> findUserDtoById(Long userId);

//    @Query(value = "SELECT u.user_id, u.name, u.designation, t.user_id AS t_id " +
//            "FROM users u " +
//            "LEFT JOIN (WITH RECURSIVE UserHierarchy AS (" +
//            "SELECT e.user_id, e.name, e.designation, e.reporting_id " +
//            "FROM users e " +
//            "WHERE e.user_id = :userId " +
//            "UNION ALL " +
//            "SELECT s.user_id, s.name, s.designation, s.reporting_id " +
//            "FROM users s " +
//            "JOIN UserHierarchy u ON s.reporting_id = u.user_id)" +
//            "SELECT user_id FROM UserHierarchy) t " +
//            "ON u.user_id = t.user_id", nativeQuery = true)
//    List<Object[]> findHierarchyDtoById(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "WITH RECURSIVE EmployeeHierarchy AS ("
            + "SELECT u.user_id AS user_id, u.name AS name, u.designation AS designation, u.reporting_id AS reporting_id, 1 AS level "
            + "FROM Users u "
            + "WHERE u.reporting_id IS NULL " // Start from the top-level managers
            + "UNION ALL "
            + "SELECT u.user_id AS user_id, u.name AS name, u.designation AS designation, u.reporting_id AS reporting_id, eh.level + 1 AS level "
            + "FROM Users u "
            + "INNER JOIN EmployeeHierarchy eh ON u.reporting_id = eh.user_id) "
            + "SELECT user_id, name AS name, designation AS designation, reporting_id AS reporting_id, level FROM EmployeeHierarchy")
    List<HierarchyDTO> findHierarchy();

}