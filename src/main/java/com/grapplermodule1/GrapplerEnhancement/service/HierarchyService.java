package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.controllers.HierarchyController;
import com.grapplermodule1.GrapplerEnhancement.customexception.TeamNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dtos.HierarchyDTO;
import com.grapplermodule1.GrapplerEnhancement.dtos.TeamMembersDTO;
import com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.TeamMemberRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HierarchyService {

    private static final Logger log = LoggerFactory.getLogger(HierarchyController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamMemberRepository teamMemberRepository;

    /**
     * For Getting Reporting Hierarchy From Top Level
     *
     * @return HierarchyDTO
     */
    public HierarchyDTO getReportingHierarchy(){
        String debugUuid = UUID.randomUUID().toString();
        final String designation = "CEO";
        try {
            Optional<Users> optionalUser = userRepository.findByDesignation(designation);
            if(optionalUser.isPresent()){
                log.info("Get Reporting Hierarchy Service Called, UUID {}", debugUuid);

                Users user = optionalUser.get();

                HierarchyDTO hierarchyDTO = new HierarchyDTO(user.getName(), user.getDesignation());
                List<HierarchyDTO> hierarchyDTOList = new ArrayList<>();

                for (Users eachUser : user.getSubordinates()) {
                    HierarchyDTO dto = convertToDTO(eachUser);
                    hierarchyDTOList.add(dto);
                }

                hierarchyDTO.setSubordinates(hierarchyDTOList);
                return hierarchyDTO;
            }
            else {
                log.error("UUID {} Get Reporting Hierarchy Service Call UserNotFoundException", debugUuid);
                throw new UserNotFoundException("User Not Found With Designation : CEO");
            }
        }
        catch (Exception e) {
            log.info("UUID {} Exception In Get Reporting Hierarchy Exception {}", debugUuid, e);
            throw e;
        }
    }

    /**
     * For Getting Reporting Hierarchy For Specified Level
     *
     * @return HierarchyDTO
     */
    public HierarchyDTO getReportingHierarchyById(Long userId){
        String debugUuid = UUID.randomUUID().toString();
        try {
            Optional<Users> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                log.info("Get Reporting Hierarchy By Id Service Called, UUID {}", debugUuid);
                Users user = optionalUser.get();

                HierarchyDTO hierarchyDTO = new HierarchyDTO(user.getName(), user.getDesignation());
                List<HierarchyDTO> hierarchyDTOList = new ArrayList<>();

                for (Users eachUser : user.getSubordinates()) {
                    HierarchyDTO dto = convertToDTO(eachUser);
                    hierarchyDTOList.add(dto);
                }

                hierarchyDTO.setSubordinates(hierarchyDTOList);
                return hierarchyDTO;
            } else {
                log.error("UUID {} Get Reporting Hierarchy By Id Service Call UserNotFoundException", debugUuid);
                throw new UserNotFoundException("User Not Found With Id: " + userId);
            }
        }
        catch (Exception e) {
            log.info("UUID {} Exception In Get Reporting Hierarchy By Id Exception {}", debugUuid, e);
            throw e;
        }
    }

    /**
     * For Converting User To DTO
     *
     * @return HierarchyDTO
     */
    private HierarchyDTO convertToDTO(Users user) {
        String debugUuid = UUID.randomUUID().toString();
        try {
            HierarchyDTO dto = new HierarchyDTO(user.getName(), user.getDesignation());

            List<Users> subordinates = user.getSubordinates();
            if (subordinates != null && !subordinates.isEmpty()) {
                for (Users subordinate : subordinates) {
                    HierarchyDTO subordinateDTO = convertToDTO(subordinate);
                    dto.getSubordinates().add(subordinateDTO);
                }
            }
            return dto;
        }
        catch (Exception e) {
            log.info("UUID {} Exception In Convert To DTO Exception {}", debugUuid, e);
            throw e;
        }
    }

    /**
     * For Getting Team Hierarchy By Id
     *
     * @return List<TeamMembersDTO>
     */
    public List<TeamMembersDTO> getTeamHierarchyById(Long teamId){
        String debugUuid = UUID.randomUUID().toString();
        try {
            Optional<List<TeamMembersDTO>> optionalListOfUsers = teamMemberRepository.findUsersByTeamId(teamId);

            if(optionalListOfUsers.isPresent() && !optionalListOfUsers.get().isEmpty()){
                log.info("Get Team Hierarchy By Id Service Called, UUID {}", debugUuid);
                return optionalListOfUsers.get();
            }
            else {
                log.error("UUID {} Get Team Hierarchy By Id Service Call TeamNotFoundException", debugUuid);
                throw new TeamNotFoundException("Team Not Found With Id : " + teamId);
            }
        }
        catch (Exception e) {
            log.error("UUID {} Exception In Get Team Hierarchy By Id Service", debugUuid);
            throw e;
        }
    }
}
