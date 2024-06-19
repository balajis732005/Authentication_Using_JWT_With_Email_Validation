package com.authentication.backend_authentication.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "FirstName is Mandatory")
    @NotBlank(message = "FirstName Should Not Blank")
    private String firstName;

    @NotEmpty(message = "LastName is Mandatory")
    @NotBlank(message = "LastName Should Not Blank")
    private String lastName;

    @Email(message = "Email is Not Formatted")
    @NotEmpty(message = "Email is Mandatory")
    @NotBlank(message = "Email Should Not Blank")
    private String email;

    @Size(min = 8, message = "Password Should Contain minimum 8 Characters Length")
    @NotEmpty(message = "Password is Mandatory")
    @NotBlank(message = "Password Should Not Blank")
    private String password;

}
