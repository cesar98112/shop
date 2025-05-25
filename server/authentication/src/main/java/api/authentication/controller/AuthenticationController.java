package api.authentication.controller;

import api.authentication.model.UserRequest;
import api.authentication.model.UserResponse;
import api.authentication.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequest userRequest){
        try{
            logger.info(userRequest.getUserName());
            UserResponse userResponse = authenticationService.createUser(userRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<>("no se pudo crear el usuario", HttpStatus.BAD_REQUEST);
        }



    }
}
