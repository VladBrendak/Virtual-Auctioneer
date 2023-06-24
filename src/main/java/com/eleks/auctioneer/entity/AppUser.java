package com.eleks.auctioneer.entity;

import com.eleks.auctioneer.DTO.AppUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String login;
    @Column(unique=true)
    private String email;
    private String user_password;

    public AppUser(AppUser userInfo) {
        this.login = userInfo.getLogin();
        this.email = userInfo.getEmail();
        this.user_password = userInfo.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static AppUserDTO mapToDto(AppUser user)
    {
        return new AppUserDTO(user.getId_user(), user.getLogin(), user.getEmail(), user.getPassword());
    }
}
