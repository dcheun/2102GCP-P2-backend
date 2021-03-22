package dev.tdz.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;

public class JwtUtil {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String secret = dotenv.get("JWT_SECRET");

    private static final Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String generateJwtToken(int id, int userRoleId){
        String token = JWT.create()
                .withClaim("id",id)
                .withClaim("userRoleId",userRoleId)
                .sign(algorithm);
        return token;
    }
    public static DecodedJWT verifyToken(String token){
        //throws JWTDecodeException
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return jwt;
    }

}
