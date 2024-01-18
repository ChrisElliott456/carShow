package com.binarychris.carShow.security;

import com.binarychris.carShow.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    // Header -> Authentication : Basic username : password
    @Autowired
    private UserServiceImpl userService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Everyone should be able to Get
        return http
                .csrf(AbstractHttpConfigurer::disable) // c ->c.disable()
                .cors(Customizer.withDefaults()) // will block other clients
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.GET, "/api/v1/car/*").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/car/*").hasRole("ADMIN") // only admin can use post
                                .anyRequest()
                                .authenticated()
                )
                .build();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    // csrf Cross site request forgery (CSRF) attack
    // cors
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(bCryptPasswordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        var user = User.builder()
//                .username("user")
//                .password(bCryptPasswordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    } made redundant with UserServiceImpl
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
// var = reads new object to know where it's coming from, dynamic keyword : var car = new Car();