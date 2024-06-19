package com.authentication.backend_authentication.user.token;

import com.authentication.backend_authentication.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    @Column(name="token_id")
    private Long id;

    @Column(name="token")
    private String token;

    @Column(name="tokenCreatedAt")
    private LocalDateTime createdAt;

    @Column(name="tokenExpiresAt")
    private LocalDateTime expiresAt;

    @Column(name="tokenValidatedAt")
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name="userId")
    private User user;

}
