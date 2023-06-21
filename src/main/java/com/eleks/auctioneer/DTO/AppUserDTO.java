package com.eleks.auctioneer.DTO;

import com.eleks.auctioneer.entity.AppUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    @Id
    private Long id;
    @NotBlank(message = "Nickname is mandatory")
    private String nickname;
    @Email(message = "Typo in email address")
    @NotBlank(message = "Email address is mandatory")
    private String email;
    @NotEmpty
    @NotBlank(message = "Password is mandatory")
    private String password;

    public static AppUser mapToUser(AppUserDTO userDTO)
    {
        AppUser user = new AppUser();
        user.setId(userDTO.getId());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

}
