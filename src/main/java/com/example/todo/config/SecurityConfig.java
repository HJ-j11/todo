package com.example.todo.config;

import com.example.todo.filter.JwtAuthenticationFilter;
import com.example.todo.handler.CustomAccessDeniedHandler;
import com.example.todo.handler.CustomAuthenticationEntryPoint;
import com.example.todo.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtils jwtUtils;
    private final CustomAuthenticationEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/favicon.ico",
                                "/error",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**", "/", "/posts/**").permitAll()
                        .requestMatchers("/member/**").permitAll()
//                        .requestMatchers("/posts/**").hasRole("USER")
                        .requestMatchers("/members/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(entryPoint);
                    exception.accessDeniedHandler(accessDeniedHandler);
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
