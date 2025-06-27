package com.lab4.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .oauth2Client(withDefaults())
                .oauth2Login(login -> login
                        .tokenEndpoint(withDefaults())
                        .userInfoEndpoint(withDefaults())
                );

        http
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                );

        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/oauth2", "/login").permitAll()
                        .anyRequest().fullyAuthenticated()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("http://localhost:8081/realms/lab/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/")
                );

        return http.build();
    }
}
