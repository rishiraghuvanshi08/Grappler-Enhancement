    package com.grapplermodule1.GrapplerEnhancement.entities;
    
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.Size;

    import java.util.List;
    
    @Entity
    @Table(name = "users")
    public class Users {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long id;
    
        @Column(name = "name")
        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name should not exceed 255 characters")
        private String name;
    
        @Column(name = "email")
        @Email(message = "Email should be a valid email address")
        @Size(max = 255, message = "Email should not exceed 255 characters")
        private String email;
    
        @Column(name = "designation")
        @Size(max = 255, message = "Designation should not exceed 255 characters")
        private String designation;
    
        @Column(name = "password")
        @NotEmpty(message = "Password is required")
        private String password;

        @OneToMany(mappedBy = "user")
        private List<Users> subordinates;

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

        public List<Users> getSubordinates() {
            return subordinates;
        }

        public void setSubordinates(List<Users> subordinates) {
            this.subordinates = subordinates;
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
                    ", subordinates=" + subordinates +
                    ", user=" + user +
                    ", role=" + role +
                    ", teamMembers=" + teamMembers +
                    ", ticket=" + ticket +
                    ", ticketAssignment=" + ticketAssignment +
                    '}';
        }
    }
