package uoc.edu.docdeskapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uoc.edu.docdeskapp.dto.UserDto;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(Model model, UserDto userDto) {
        //userDto.setRol(1);
        model.addAttribute("user", userDto);
        logger.debug("|||||||||| " + userDto);
        return "login";
    }

//    @RequestMapping("/error")
//    public String error() {
//        return "login";
//    }
}
