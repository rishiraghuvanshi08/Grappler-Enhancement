    package com.grapplermodule1.GrapplerEnhancement.entities;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import lombok.Getter;

    import java.util.List;
    
    @Entity
    @Table(name = "users")
    public class Users {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long id;

        @Column(name = "name")
        private String name;
    
        @Column(name = "email")
        private String email;
    
        @Column(name = "designation")
        private String designation;
    
        @Column(name = "password")
        private String password;

        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<Users> usersList;

        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "reporting_id")
        private Users user;

        @JsonIgnore
        @OneToOne(mappedBy = "user")
        private Role role;

        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<TeamMembers> teamMembers;

        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<Ticket> ticket;

        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<TicketAssignment> ticketAssignment;
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getDesignation() {
            return designation;
        }
    
        public void setDesignation(String designation) {
            this.designation = designation;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }

        public List<Users> getUsersList() {
            return usersList;
        }

        public void setUsersList(List<Users> usersList) {
            this.usersList = usersList;
        }

        public Users getUser() {
            return user;
        }

        public void setUser(Users user) {
            this.user = user;
        }

        public List<TeamMembers> getTeamMembers() {
            return teamMembers;
        }

        public void setTeamMembers(List<TeamMembers> teamMembers) {
            this.teamMembers = teamMembers;
        }

        public List<Ticket> getTicket() {
            return ticket;
        }

        public void setTicket(List<Ticket> ticket) {
            this.ticket = ticket;
        }

        public List<TicketAssignment> getTicketAssignment() {
            return ticketAssignment;
        }

        public void setTicketAssignment(List<TicketAssignment> ticketAssignment) {
            this.ticketAssignment = ticketAssignment;
        }

        public Role getRole() {
            return role;
        }
    
        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", designation='" + designation + '\'' +
                    ", password='" + password + '\'' +
                    ", usersList=" + usersList +
                    ", user=" + user +
                    ", role=" + role +
                    ", teamMembers=" + teamMembers +
                    ", ticket=" + ticket +
                    ", ticketAssignment=" + ticketAssignment +
                    '}';
        }
    }
