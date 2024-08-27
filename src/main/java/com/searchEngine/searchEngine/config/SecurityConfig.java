package com.searchEngine.searchEngine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.searchEngine.searchEngine.filter.ApiKeyFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/").permitAll()
            .requestMatchers("/api/**").authenticated()
            .requestMatchers("/panel/**").authenticated()
            .and()
            .addFilterBefore(apiKeyFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public ApiKeyFilter apiKeyFilter(){
        return new ApiKeyFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
