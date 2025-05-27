package api.gateway.security;

import api.gateway.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class JwtFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final List<String> PUBLIC_PATH = List.of("/api/auth");

    @Autowired
    private WebClient.Builder webClientConfig;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String currentPath = exchange.getRequest().getPath().value();

        for(String path : PUBLIC_PATH){
            if(currentPath.contains(path)){
                return chain.filter(exchange);
            }
        }

        String token = extractToken(exchange.getRequest());



        return webClientConfig.build().get().uri("http://authentication/api/auth/validate/"+token).retrieve().bodyToMono(UserResponse.class).flatMap(
                user ->{
                    Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(),null,authorities);
                    SecurityContext context = new SecurityContextImpl(authentication);

                    return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
                }
        ).onErrorResume( execption -> {
            logger.error("Error al validar token: " + execption.getMessage());
            var response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");

            String mensaje = "{\"error\": \"Token inválido o no autorizado\"}";
            var buffer = response.bufferFactory().wrap(mensaje.getBytes());

            return response.writeWith(Mono.just(buffer));
        });


    }

    private String extractToken(ServerHttpRequest request){

        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        return null;
    }

}
