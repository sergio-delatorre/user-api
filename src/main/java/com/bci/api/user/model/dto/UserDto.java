package com.bci.api.user.model.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(regexp = ValidationRegex.EMAIL_REGEX, message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = ValidationRegex.PASSWORD_REGEX, message = "Invalid password format")
    private String password;

    @Valid
    private List<PhoneDto> phones;
}
