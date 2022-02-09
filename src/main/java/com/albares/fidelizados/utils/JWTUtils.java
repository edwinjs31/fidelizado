package com.albares.fidelizados.utils;

import com.albares.fidelizados.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public final class JWTUtils {

    private static final Algorithm algorithm = Algorithm.HMAC256(Secrets.JWT_SECRET);
    private static final JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(Parameters.PROJECT_NAME)
            .build();

    public JWTUtils() {
    }

    public static final String generateToken(int id, int loginId,int app) {
        String token = JWT.create()
                .withIssuer(Parameters.PROJECT_NAME)
                .withClaim("app", app)
                .withClaim("id", id)
                .withClaim("loginId", loginId)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    public static final Integer checkJWTandGetUserId(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("id").asInt();
    }

    public static final User checkJWTandGetUser(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return new User(jwt.getClaim("id").asInt());
        } catch (JWTVerificationException ex) {
            //TODO: Avisar con bandera roja
            return new User(-1);
        }
    }

}
