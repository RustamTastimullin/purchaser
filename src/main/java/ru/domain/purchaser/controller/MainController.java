package ru.domain.purchaser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.domain.purchaser.model.Role;
import ru.domain.purchaser.model.User;
import ru.domain.purchaser.repository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(
            @RequestParam(name = "name", required = false, defaultValue = "World!")
                    String name,
                    Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute( "title", "About!");
        return "about";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "auth-forms/registration-form";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model msg) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null) {
            msg.addAttribute("msg", "User exists!");
            return "auth-forms/registration-form";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

}
