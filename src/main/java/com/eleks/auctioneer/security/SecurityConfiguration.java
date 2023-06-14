package com.eleks.auctioneer.security;
import com.eleks.auctioneer.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    /*
     * Hardcode first user to check if security works
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails user = User
                .withUsername("Vlad")
                .password(encoder.encode("1234"))
                .build();

        return new InMemoryUserDetailsManager(user);
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        return new AppUserService();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/other").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/**")
                .authenticated().and().formLogin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
