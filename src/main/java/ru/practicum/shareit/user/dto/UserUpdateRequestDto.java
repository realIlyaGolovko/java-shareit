package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateRequestDto {
    private String name;
    @Email(message = "email must be correct email")
    private String email;
}
