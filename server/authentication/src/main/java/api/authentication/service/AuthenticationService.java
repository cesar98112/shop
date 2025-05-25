package api.authentication.service;

import api.authentication.model.Role;
import api.authentication.model.UserModel;
import api.authentication.model.UserRequest;
import api.authentication.model.UserResponse;
import api.authentication.repository.UserRepository;
import api.authentication.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtBuilder jwtBuilder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
               bCryptPasswordEncoder.encode(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getName(),
                userRequest.getLastName(),
                Set.of(Role.USER));
        return userRepository.save(userModel);
    }

    private UserModel createUserWithAdminRole(UserRequest userRequest){
        UserModel userModel = new UserModel(userRequest.getUserName(),
                bCryptPasswordEncoder.encode(userRequest.getPassword()),
                userRequest.getEmail(),
                userRequest.getName(),
                userRequest.getLastName(),
                Set.of(Role.ADMIN));
        return userRepository.save(userModel);
    }
}
