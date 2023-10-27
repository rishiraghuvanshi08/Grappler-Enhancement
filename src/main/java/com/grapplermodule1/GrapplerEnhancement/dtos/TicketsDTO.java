    package com.grapplermodule1.GrapplerEnhancement.dtos;

    import com.grapplermodule1.GrapplerEnhancement.entities.TicketAssignment;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.NotNull;
    import lombok.Data;

    import java.util.List;

    public class TicketsDTO {
        private Long id;

        @NotEmpty(message = "Ticket name is required")
        private String name;

        @NotNull(message = "Project id is required")
        private Long projectId;

        @NotNull(message = "Creator id is required")
        private Long creatorId;

        public Long getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Long creatorId) {
            this.creatorId = creatorId;
        }

        public Long getProjectId() {
            return projectId;
        }

        public void setProjectId(Long projectId) {
            this.projectId = projectId;
        }

        public TicketsDTO() {
        }

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

        public TicketsDTO(Long id, String name, Long projectId, Long creatorId) {
            this.id = id;
            this.name = name;
            this.projectId = projectId;
            this.creatorId = creatorId;
        }
    }

