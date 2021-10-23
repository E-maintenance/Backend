package runtimeTerror.autoCare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.ApplicationUser;
import runtimeTerror.autoCare.repository.ApplicationUserRepo;

import java.util.ArrayList;

@Controller
public class Service {

    @Autowired
    ApplicationUserRepo applicationUserRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/signup")
    public String signUpPage(@AuthenticationPrincipal ApplicationUser user , Model model) {
        if (user != null) {
            ApplicationUser findUser = applicationUserRepo.findByUsername(user.getUsername());
            model.addAttribute("user", findUser.getUsername());
        }
        return "signup";
    }

    @GetMapping("/login")
    public String logInPage(@AuthenticationPrincipal ApplicationUser user , Model model) {
        if (user != null) {
            ApplicationUser findUser = applicationUserRepo.findByUsername(user.getUsername());
            model.addAttribute("user", findUser.getUsername());
        }
        return "login";
    }

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal ApplicationUser user , Model model){
        if (user != null) {
            ApplicationUser findUser = applicationUserRepo.findByUsername(user.getUsername());
            model.addAttribute("user", findUser.getId());
        }
        return "home.html";
    }


    @PostMapping("/signup")
    public RedirectView signUp(@ModelAttribute ApplicationUser applicationUser) {
        ApplicationUser newUser = new ApplicationUser(applicationUser.getUsername(),
                encoder.encode(applicationUser.getPassword()));

        applicationUserRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/login");
    }
    @GetMapping("/service")
    @ResponseBody
    public String getHelloWorld() {
        return "service.html";
    }
}
