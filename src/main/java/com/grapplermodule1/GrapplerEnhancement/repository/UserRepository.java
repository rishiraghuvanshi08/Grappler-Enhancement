package com.grapplermodule1.GrapplerEnhancement.repository;

import com.grapplermodule1.GrapplerEnhancement.dto.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dto.UsersDTO(e.id, e.name, e.designation, e.email) " +
       "FROM Users e")
       Optional<List<UsersDTO>> findAllUser();

    @Query("SELECT NEW com.grapplermodule1.GrapplerEnhancement.dto.UsersDTO(e.id, e.name, e.designation, e.email) " +
            "FROM Users e WHERE e.id = :userId")
     Optional<UsersDTO> findUserDtoById(Long userId);
}
