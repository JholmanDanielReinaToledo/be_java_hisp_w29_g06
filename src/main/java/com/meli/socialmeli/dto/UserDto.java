package com.meli.socialmeli.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer user_id;

    @NotBlank
    @Size(max = 15, message = "El nombre no debe tener más de 15 caracteres")
    private String user_name;

}
