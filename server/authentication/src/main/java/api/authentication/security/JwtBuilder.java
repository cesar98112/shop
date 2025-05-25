package api.authentication.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtBuilder {
    @Value("${security.jwt.key.private}")
    private String jwtKey;

    @Value("${security.jwt.user.generator}")
    private String jwtGenerator;

    public String CreateToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.jwtKey);

        String username = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwtTokens = JWT.create().withIssuer(jwtGenerator)
                .withSubject(username)
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return jwtTokens;
    }


    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(jwtGenerator).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            return decodedJWT;
        }catch (JWTVerificationException exception){
            throw new RuntimeException("token invalido");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }
    public Claim expesificClaim(DecodedJWT decodedJWT, String nameClaim){
        return decodedJWT.getClaim(nameClaim);
    }
    public Map<String, Claim> extractAllClaim(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
