package uoc.edu.docdeskapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @RequestMapping("/users")
    public String users() {
        return "users";
    }
}
