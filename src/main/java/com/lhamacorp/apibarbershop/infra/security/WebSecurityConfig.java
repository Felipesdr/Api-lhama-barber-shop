package com.lhamacorp.apibarbershop.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    SecurityFIlter securityFIlter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm-> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register/client").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register/barber").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/barber/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/barberUnavailableTime/register").hasRole("BARBER")
                        .requestMatchers(HttpMethod.POST,"/barberUnavailableTime/**").hasRole("BARBER")
                        .requestMatchers(HttpMethod.PUT,"/barberUnavailableTime/update").hasRole("BARBER")
                        .requestMatchers(HttpMethod.DELETE, "/barberUnavailableTime/delete/**").hasRole("BARBER")
                        .requestMatchers(HttpMethod.GET, "/client/email/**").hasRole("BARBER")
                        .requestMatchers(HttpMethod.GET, "/client/name/**").hasRole("BARBER")
                        .requestMatchers(HttpMethod.GET, "/unavailableTime/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/unavailableTime/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/unavailableTime/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/unavailableTime/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/schedule/confirm/**").hasRole("BARBER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFIlter, UsernamePasswordAuthenticationFilter.class)
                .headers(h-> h.disable());



        return http.build();
    }

    @Bean
    public AuthenticationManager getAutheticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
