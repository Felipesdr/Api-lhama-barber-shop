package com.lhamacorp.apibarbershop.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lhamacorp.apibarbershop.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("Lhama barber shop")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .withClaim("User id",  user.getIdUser())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token){

        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("Lhama barber shop")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }

    }

    public String recoverToken(HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null){
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }

    public Long getIdFromToken(HttpHeaders header) {

        String token = header.get("Authorization").get(0);
        String jwt = token.replace("Bearer ", "");

        System.out.println(jwt);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            Long idUser = JWT.require(algorithm)
                    .withIssuer("Lhama barber shop")
                    .build()
                    .verify(jwt)
                    .getClaim("User id")
                    .asLong();

            System.out.println("ID USER = " + idUser);
            return idUser;
        } catch (JWTVerificationException exception){

            return null;
        }

    }


    private Instant expirationDate() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
