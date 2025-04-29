package com.example.authenticationservice.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors().disable();
        http.csrf().disable();
        http.authorizeHttpRequests(a-> a.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecretKey secretKey() {

        // here we are creating a random key which is not good practice.
        MacAlgorithm algorithm= Jwts.SIG.HS256;
        String secreat="mysecretkey";
        SecretKeySpec key=new SecretKeySpec(secreat.getBytes(),algorithm.toString());
//        return Jwts.SIG.HS256.key().build();
        return key;
    }
}
