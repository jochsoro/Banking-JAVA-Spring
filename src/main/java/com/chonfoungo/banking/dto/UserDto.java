package com.chonfoungo.banking.dto;


import com.chonfoungo.banking.models.Role;
import com.chonfoungo.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {


    private Integer id;

    @NotNull // null
    @NotEmpty // ""
    @NotBlank // " "
    private String firstName;

    @NotNull // null
    @NotEmpty // ""
    @NotBlank(message = "Le prénom ne doit pas être vide") // " "
    private String lastName;

    @NotNull // null
    @NotEmpty // ""
    @NotBlank // " "
    @Email
    private String email;

    @NotNull // null
    @NotEmpty // ""
    @NotBlank // " "
    @Size(min = 7, max = 16)
    private String password;


    @Past
    private LocalDateTime birthday;

    public static UserDto fromEntity(User user) {
        // null check
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user) {
        // null check
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
