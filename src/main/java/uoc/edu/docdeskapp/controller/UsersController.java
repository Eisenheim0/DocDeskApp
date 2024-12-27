package uoc.edu.docdeskapp.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uoc.edu.docdeskapp.config.CustomUserDetails;
import uoc.edu.docdeskapp.dto.UserDto;
import uoc.edu.docdeskapp.entity.RolEntity;
import uoc.edu.docdeskapp.entity.UsuarioEntity;
import uoc.edu.docdeskapp.repositories.UserRepository;
import uoc.edu.docdeskapp.services.RoleService;
import uoc.edu.docdeskapp.services.UserService;

import java.util.List;

@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/users")
    public String users(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("|||||||||| " + ((CustomUserDetails) principal).getFullname());
        if (principal instanceof UserDetails) {
            String username = ((CustomUserDetails) principal).getFullname();
            model.addAttribute("username", username);
        } else {
            model.addAttribute("username", "Guest");
        }

        List<UsuarioEntity> users = userService.findAll();
        model.addAttribute("users", users);

        List<RolEntity> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "users";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(id);
            redirectAttributes.addAttribute("successMessage", "User deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Error deleting user.");
        }
        return "redirect:/users";
    }

    @PostMapping("/users/create")
    public String createUser(@Valid UserDto userDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> logger.debug(error.toString()));
            redirectAttributes.addAttribute("errorMessage", "Error creating user. Errors in fields.");
            return "redirect:/users";
        }

        try {
            userService.save(userDto);
            redirectAttributes.addAttribute("successMessage", "User created successfully.");
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Error creating user. " + e.getMessage());
        }

        return "redirect:/users";
    }
}
