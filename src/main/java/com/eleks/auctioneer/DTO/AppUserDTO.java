package com.eleks.auctioneer.DTO;

import com.eleks.auctioneer.entity.AppUser;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private Long id_user;
    @NotBlank(message = "Nickname is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String login;
    @Email(message = "Typo in email address")
    @NotBlank(message = "Email address is mandatory")
    private String email;
    @NotEmpty
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',.?/*~$^+=<>]).{6,19}$")
    private String user_password;

    public static AppUser mapToUser(AppUserDTO userDTO, PasswordEncoder passwordEncoder)
    {
        AppUser user = new AppUser();
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setUser_password(passwordEncoder.encode(userDTO.getUser_password()));
        return user;
    }

}