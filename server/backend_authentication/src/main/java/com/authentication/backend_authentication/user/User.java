package com.authentication.backend_authentication.user;

import com.authentication.backend_authentication.role.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="user_table")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // For this add @EnableJpaAuditing in main SpringBoot Application
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="fistName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="dob")
    private LocalDate dateOfBirth;

    @Column(unique = true,name="user_email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="accountLocked")
    private boolean accountLocked;

    @Column(name="accountEnabled")
    private boolean enabled;

    //By EntityListeners
    @CreatedDate
    @Column(nullable = false, updatable = false, name="userCreatedDate")
    private LocalDateTime createdDate;

    //By EntityListeners
    @LastModifiedDate
    @Column(insertable = false, name="userLastModifiedDate")
    private LocalDateTime lastModifiedDate;

    //From Role and We are Eagerly Fetching the Role
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
