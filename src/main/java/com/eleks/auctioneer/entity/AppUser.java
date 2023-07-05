package com.eleks.auctioneer.entity;

import com.eleks.auctioneer.DTO.AppUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "login")
    private String username;
    @Column(name = "email", unique=true)
    private String email;
    @Column(name = "user_password")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Lot> lots;

    public AppUser(AppUser userInfo) {
        this.username = userInfo.getUsername();
        this.email = userInfo.getEmail();
        this.password = userInfo.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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

    public static AppUserDTO mapToDTO(AppUser user)
    {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(user.getId());
        appUserDTO.setUsername(user.getUsername());
        appUserDTO.setEmail(user.getEmail());
        appUserDTO.setPassword("**********");
        return appUserDTO;
    }
}
