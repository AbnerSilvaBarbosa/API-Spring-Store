package com.example.springboot.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UserRecordDto(
        @NotBlank(message = "Name required")
        String name,
        @NotBlank(message = "Email required")
        @Email(message = "Email invalid")
        String email,

        @NotEmpty(message = "Password required")
        String password,

        @NotNull
        BigDecimal money

        ) {
}
