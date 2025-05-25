package api.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/auth")
public class TestController {

    @GetMapping("/hello")
    public String getHello(){
        return "hola mundo";
    }
}
