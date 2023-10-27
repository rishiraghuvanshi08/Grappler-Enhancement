package com.grapplermodule1.GrapplerEnhancement.service;

import com.grapplermodule1.GrapplerEnhancement.controllers.TicketController;
import com.grapplermodule1.GrapplerEnhancement.customexception.ProjetcNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.customexception.TicketNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.customexception.UserNotFoundException;
import com.grapplermodule1.GrapplerEnhancement.dtos.TicketsDTO;
import com.grapplermodule1.GrapplerEnhancement.dtos.UsersDTO;
import com.grapplermodule1.GrapplerEnhancement.entities.Project;
import com.grapplermodule1.GrapplerEnhancement.entities.Ticket;
import com.grapplermodule1.GrapplerEnhancement.entities.Users;
import com.grapplermodule1.GrapplerEnhancement.repository.ProjectRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.TicketRepository;
import com.grapplermodule1.GrapplerEnhancement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);


    /**
     * For Fetching Tickets By Project id
     *
     * @return List<Ticket>
     */
    public List<TicketsDTO> fetchTicketsByProjectId(Long projectId) {
        try {
            log.info("Fetch Tickets By Project Id Service Called, Project Id {}", projectId);
                Optional<Project> project = projectRepository.findById(projectId);
            if (project.isEmpty()) {
                throw new ProjetcNotFoundException("Project Not Found With Id :" + projectId);
            }
            Optional<List<TicketsDTO>> listOfTickets = ticketRepository.findByProjectId(projectId);
            if (listOfTickets.isPresent() && !listOfTickets.get().isEmpty()) {
                log.info("Fetch Tickets By Project Id Service Returning List<TicketsDTO>");
                return listOfTickets.get();
            } else {
                log.error("Fetch Tickets By Project Id throws TicketNotFoundException");
                throw new TicketNotFoundException("Tickets Not Found With Project Id : " + projectId);
            }
        } catch (Exception e) {
            log.error("Exception in Fetch By Project Id Exception {}", e.getMessage());
            throw e;
        }
    }


    /**
     * For Fetching Tickets By User id
     *
     * @return List<TicketsDTO>
     */
    public List<TicketsDTO> fetchTicketsByUserId(Long userId) {
        try {
            log.info("Fetch Tickets By userId Id Service Called, userId Id {}", userId);
            Optional<Users> usersDTO = userRepository.findById(userId);
            if (usersDTO.isEmpty()) {
                throw new UserNotFoundException("User Not Found With Id :" + userId);
            }
            Optional<List<TicketsDTO>> listOfTickets = ticketRepository.findByUserId(userId);
            if (listOfTickets.isPresent() && !listOfTickets.get().isEmpty()) {
                log.info("Fetch Tickets By User Id Service Returning List<TicketsDTO>");
                return listOfTickets.get();
            } else {
                log.error("Fetch Tickets By User Id throws TicketNotFoundException");
                throw new TicketNotFoundException("Tickets Not Found With User Id : " + userId);
            }
        } catch (Exception e) {
            log.error("Exception in Fetch By User Id Exception {}", e.getMessage());
            throw e;
        }
    }

    public Ticket createTicketsInProject(TicketsDTO ticket) {
        try {
            log.info("Create Ticket By Project Id Service Called, Project Id {}", ticket.getProjectId());
            log.info("Create Ticket By Project Id Service Called, Project Id {}", ticket.getCreatorId());
            Optional<Project> project = projectRepository.findById(ticket.getProjectId());
            if (project.isEmpty()) {
                throw new ProjetcNotFoundException("Project Not Found With Id :" + ticket.getProjectId());
            }

            Optional<Users> usersDTO = userRepository.findById(ticket.getCreatorId());
            if (usersDTO.isEmpty()) {
                throw new UserNotFoundException("User Not Found With Id :" + ticket.getCreatorId());
            }

            Ticket newTicket=new Ticket();
            newTicket.setName(ticket.getName());

            Project project1=new Project();
            project1.setId(ticket.getProjectId());

            Users user=new Users();
            user.setId(ticket.getCreatorId());
            newTicket.setProject(project1);
            newTicket.setUser(user);
            return  ticketRepository.save(newTicket);

        } catch (Exception e) {
            log.error("Exception in Fetch By Project Id Exception {}", e.getMessage());
            throw e;
        }
    }

    public Boolean deleteTicketById(Long ticketId) {
        try {
            log.info("Delete Ticket Service Called, Ticket Id {}", ticketId);
            Optional<Ticket> ticket=ticketRepository.findById(ticketId);

            if(ticket.isPresent()){
                ticketRepository.deleteById(ticketId);
                log.info("Delete Ticket Service Returning True");
                return true;
            }
            else {
                log.error("Delete Ticket throws TicketNotFoundException In Service");
                throw new TicketNotFoundException("Ticket Not Found With ID : " + ticketId);
            }
        }
        catch (Exception e) {
            log.error("Exception in Delete Ticket Exception In Service {}", e.getMessage());
            throw e;
        }
    }

}
