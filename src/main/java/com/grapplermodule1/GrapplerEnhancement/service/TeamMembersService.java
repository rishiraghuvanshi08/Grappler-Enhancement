package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.customexception.MemberAlreadyPresentException;
import com.grapplermodule1.GrapplerEnhancement.customexception.MemberNotPresentInTeamException;
import com.grapplermodule1.GrapplerEnhancement.customexception.TeamNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dtos.TeamMembersDTO;
import com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Team;
import com.grapplermodule1.GrapplerEnhancement.entities.TeamMembers;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.TeamMemberRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.TeamRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamMembersService {
    private static final Logger log = LoggerFactory.getLogger(HierarchyService.class);

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

@Autowired
private ModelMapper modelMapper;

    /**
     * For Adding New Team Member
     *
     * @return TeamMembers
     */
    public TeamMembers addNewMember(Long teamId, Long userId) {
        try {
            log.info("Add Team Member Service Called");
            Optional<TeamMembers> optionalTeamMember = teamMemberRepository.findByTeamIdAndUserId(teamId, userId);

            if(!optionalTeamMember.isPresent()) {
                Optional<Team> optionalTeam = teamRepository.findById(teamId);

                if (optionalTeam.isPresent()) {
                    Optional<Users> optionalUser = userRepository.findById(userId);
                    if (optionalUser.isPresent()) {
                        TeamMembers teamMembers = new TeamMembers(optionalTeam.get(), optionalUser.get());

                        return teamMemberRepository.save(teamMembers);
                    } else {
                        log.error("Add Team Member Service throws UserNotFoundException");
                        throw new UserNotFoundException("User Not Found With ID : " + userId);
                    }
                } else {
                    log.error("Add Team Member Service throws TeamNotFoundException");
                    throw new TeamNotFoundException("Team Not Found With ID : " + teamId);
                }
            }
            else {
                log.error("Add Team Member Service throws MemberAlreadyPresentException");
                throw new MemberAlreadyPresentException("Member Already Present In Same Team.");
            }
        }
        catch (Exception e) {
            log.error("Exception In Add Team Member Exception {}", e.getMessage());
            throw e;
        }
    }

    /**
     * For Get Member Details
     *
     * @return UserDTO
     */
    public UsersDTO getMemberDetail(Long teamId, Long userId) {
        try {
            log.info("Get Member Details Service Called, User Id {}", userId);
            Optional<Team> optionalTeam = teamRepository.findById(teamId);

            if(optionalTeam.isPresent()){
                Optional<TeamMembers> optionalTeamMember = teamMemberRepository.findByTeamIdAndUserId(teamId, userId);

                if (optionalTeamMember.isPresent()) {
                    Optional<UsersDTO> optionalUserDTO = userRepository.findUserDtoById(userId);

                    if (optionalUserDTO.isPresent()) {
                        log.info("Get Member Details Service Returning UserDTO, User Id {}", userId);
                        return optionalUserDTO.get();
                    }
                    else {
                        log.error("Get Member Details Service throws UserNotFoundException");
                        throw new UserNotFoundException("User Not Found With ID : " + userId);
                    }
                }
                else {
                    log.error("Get Member Details Service throws TeamNotFoundException");
                    throw new MemberNotPresentInTeamException("User With ID : " + userId + " Not Found In Team With ID : " + teamId);
                }
            }
            else {
                log.error("Get Member Details Service throws TeamNotFoundException");
                throw new TeamNotFoundException("Team Not Found With ID : " + teamId);
            }
        } catch (Exception e) {
            log.error("Exception in Fetch User By Id Exception {}", e.getMessage());
            throw e;
        }
    }

    /**
     * For Deleting Team Member
     *
     * @return Boolean
     */
    public Boolean deleteTeamMember(Long teamId, Long userId) {
        try {
            log.info("Delete Team Member Service Called");
            Optional<Team> optionalTeam = teamRepository.findById(teamId);

            if(optionalTeam.isPresent()) {
                Optional<Users> optionalUser = userRepository.findById(userId);

                if (optionalUser.isPresent()) {
                    Optional<TeamMembers> optionalTeamMember = teamMemberRepository.findByTeamIdAndUserId(teamId, userId);

                    if (optionalTeamMember.isPresent()) {
                        teamMemberRepository.deleteByTeamIdAndUserId(teamId, userId);
                        return true;
                    } else {
                        log.error("Add Team Member Service throws UserNotFoundException");
                        throw new MemberNotPresentInTeamException("User With Id : "+ userId +" is not Present in Team With ID : " + teamId);
                    }
                } else {
                    log.error("Add Team Member Service throws TeamNotFoundException");
                    throw new UserNotFoundException("User Not Found With ID : " + userId);
                }
            }
            else {
                log.error("Add Team Member Service throws MemberAlreadyPresentException");
                throw new TeamNotFoundException("Team Not Found With Id : " + teamId);
            }
        }
        catch (Exception e) {
            log.error("Exception In Delete Team Member Exception {}", e.getMessage());
            throw e;
        }
    }

    /**
     * For Get Member Details
     *
     * @return UserDTO
     */
    public List<TeamMembersDTO> fetchMembersByProjectId( Long projectId) {
        try {

            List<TeamMembers> teamMembers = teamMemberRepository.findTeamMembersByProjectId(projectId);
            log.info("Team members {},", teamMembers);
            List<TeamMembersDTO> teamMembersDTOs = teamMembers.stream()
                    .map(teamMember -> modelMapper.map(teamMember, TeamMembersDTO.class))
                    .toList();

            return teamMembersDTOs;

        } catch (Exception e) {
            log.error("Exception in Fetch User By Id Exception {}", e.getMessage());
            throw e;
        }
    }
}
