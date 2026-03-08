package com.user.service.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }


    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
     public SecurityFilterChain configure(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
                 .csrf(csrf -> csrf.disable());
         return http.build();
     }

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
     }
}
