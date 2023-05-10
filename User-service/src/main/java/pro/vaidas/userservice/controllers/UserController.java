package pro.vaidas.userservice.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/**")
    public ModelAndView index(@AuthenticationPrincipal UserDetails user){
        ModelAndView nextPage = new ModelAndView("index");
        nextPage.addObject("user", user);
        return nextPage;
    }

    @GetMapping("/admin")
    public ModelAndView rolePage(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView nextPage = new ModelAndView("admin");
        nextPage.addObject("user", userDetails);
        return nextPage;
    }
}
