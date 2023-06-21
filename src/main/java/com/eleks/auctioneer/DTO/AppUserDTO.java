package com.eleks.auctioneer.DTO;

import com.eleks.auctioneer.entity.AppUser;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {

    private Long id;
    @NotBlank(message = "Nickname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String nickname;
    @Email(message = "Typo in email address")
    @NotBlank(message = "Email address is mandatory")
    private String email;
    @NotEmpty
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',.?/*~$^+=<>]).{6,20}$")
    private String password;

    public static AppUser mapToUser(AppUserDTO userDTO)
    {
        AppUser user = new AppUser();
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
