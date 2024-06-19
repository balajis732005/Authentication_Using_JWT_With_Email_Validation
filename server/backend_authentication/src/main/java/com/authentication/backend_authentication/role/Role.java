package com.authentication.backend_authentication.role;

import com.authentication.backend_authentication.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // For this add @EnableJpaAuditing in main SpringBoot Application
public class Role {

    @Id
    @GeneratedValue
    @Column(name="role_id")
    private Long id;

    @Column(unique = true, name="role")
    private String name;

    //Mapping the users with roles in User
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore // By this we can ignore form Bean Serialization
    @Column(name="listOfRoles")
    private List<User> users;

    //By EntityListeners
    @CreatedDate
    @Column(nullable = false, updatable = false, name="roleCreatedDate")
    private LocalDateTime createdDate;

    //By EntityListeners
    @LastModifiedDate
    @Column(insertable = false, name="roleLastModifiedDate")
    private LocalDateTime lastModifiedDate;

}
