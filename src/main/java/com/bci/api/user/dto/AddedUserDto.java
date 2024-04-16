package com.bci.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddedUserDto {

    private String name;
    private String email;
    private String password;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean isActive = true;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
}
