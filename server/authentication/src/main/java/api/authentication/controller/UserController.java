package api.authentication.controller;


import api.authentication.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtBuilder jwtBuilder;

    @GetMapping("/getUser")
    public ResponseEntity<User>

}
