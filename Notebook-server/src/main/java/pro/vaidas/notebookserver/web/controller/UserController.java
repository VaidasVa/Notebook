package pro.vaidas.notebookserver.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pro.vaidas.notebookserver.business.service.UserService;
import pro.vaidas.notebookserver.model.User;

@Controller
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public String index(Model model){
        return "index";
    }

    @GetMapping("/register")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView saveUser(Model model, User user){
        service.saveUser(user);
        return new RedirectView("/");
    }
}
