package com.bci.api.user.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddedUserDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;
    @JsonProperty("isActive")
    private boolean isActive = true;

    @JsonProperty("last_login")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
        if (modified == null) {
            lastLogin = created;
        } else {
            lastLogin = modified;
        }
    }
}
