package api.authentication.service;

import api.authentication.controller.AuthenticationController;
import api.authentication.model.*;
import api.authentication.repository.UserRepository;
import api.authentication.security.JwtBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtBuilder jwtBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    public UserResponse login(UserLogin userLogin){
        UserDetails user = userDetailsService.loadUserByUsername(userLogin.getUsername());


        if(passwordEncoder.matches(userLogin.getPassword(),user.getPassword())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            String token = jwtBuilder.CreateToken(authentication);
            UserModel userModel = userRepository.findUserByUsername(user.getUsername()).orElseThrow();
            return new UserResponse(userModel.getUsername(),userModel.getName(),userModel.getLastname(),userModel.getEmail(),token);
        }

        return null;

    }

    public UserResponse createUser(UserRequest userRequest){
        UserModel user = createUserWithUserRole(userRequest);

        Authentication authentication = new  UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        String token = jwtBuilder.CreateToken(authentication);

        UserResponse userResponse = new UserResponse(user.getUsername(),user.getName(),user.getLastname(),user.getEmail(),token);

        return userResponse;
    }


    private UserModel createUserWithUserRole(UserRequest userRequest){
        UserModel userModel = new UserModel(userRequest.getUserName(),
               passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getName(),
                userRequest.getLastName(),
                Set.of(Role.USER));
        return userRepository.save(userModel);
    }

    private UserModel createUserWithAdminRole(UserRequest userRequest){
        UserModel userModel = new UserModel(userRequest.getUserName(),
               passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getName(),
                userRequest.getLastName(),
                Set.of(Role.ADMIN));
        return userRepository.save(userModel);
    }
}
