package com.lizijian.officeauto.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizijian.officeauto.pojo.Role;
import com.lizijian.officeauto.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class JwtTool {

    @Autowired
    private  ObjectMapper jsonObjectMapper;

    @Value("${Algorithm.HMAC.publicKey}")
    private  String publicKey;

    public  String generateJWT(User user) throws JsonProcessingException {
        String userString = jsonObjectMapper.writeValueAsString(user);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", user.getId());
        builder.withClaim("username", user.getUsername());
        builder.withClaim("role", jsonObjectMapper.writeValueAsString(user.getRoles().get(0)));
        builder.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000L));
        return builder.sign(Algorithm.HMAC256(publicKey));
    }

    public  User parseJwt(String token) throws IOException {
        Algorithm algorithm = Algorithm.HMAC256(publicKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        User user = new User();
        user.setId(jwt.getClaim("id").asInt());
        user.setUsername(jwt.getClaim("username").asString());
        String string = jwt.getClaim("role").asString();
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(jsonObjectMapper.readValue(string, Role.class));
        user.setRoles(roles);
        return user;
    }

}
