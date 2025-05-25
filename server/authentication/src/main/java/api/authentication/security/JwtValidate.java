package api.authentication.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtValidate extends OncePerRequestFilter {


    private JwtBuilder jwtBuilder;

    public JwtValidate (JwtBuilder jwtBuilder){
       this.jwtBuilder = jwtBuilder;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token != null){
            token = token.substring(7);
            DecodedJWT decodedJWT = jwtBuilder.validateToken(token);

            String username = jwtBuilder.extractUsername(decodedJWT);
            String stringAuthorization = jwtBuilder.expesificClaim(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorization);

            SecurityContext context = SecurityContextHolder.getContext();

            Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request,response);
    }
}
