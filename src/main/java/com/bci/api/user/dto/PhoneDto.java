package com.bci.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    @NotBlank(message = "Number cannot be blank")
    @Size(max = 15, message = "Number must be less than 15 characters long")
    private String number;

    @NotBlank(message = "City code cannot be blank")
    @Size(max = 10, message = "City code must be less than 10 characters long")
    private String citycode;

    @NotBlank(message = "Country code cannot be blank")
    @Size(max = 10, message = "Country code must be less than 10 characters long")
    private String countrycode;
}
