package runtimeTerror.autoCare.controller.User.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import runtimeTerror.autoCare.controller.admin.controller.EmailServiceImpl;
import runtimeTerror.autoCare.dto.UserRegistrationDto;
import runtimeTerror.autoCare.model.Role;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.Verification;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.repository.RoleRepository;
import runtimeTerror.autoCare.repository.UserRepository;
import runtimeTerror.autoCare.repository.VerifiedRepository;
import runtimeTerror.autoCare.repository.WorkShopRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerifiedRepository verifiedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailServiceImpl service;

    @Autowired
    WorkShopRepository workShopRepository;

    @GetMapping("/User/register")
    public String registerAdmin(Model model){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto", userRegistrationDto);
        return  "User/auth/register";
    }

    @PostMapping("/User/register")
    public String RegisterAdmin(@Valid @ModelAttribute User user, BindingResult result, Model model, HttpServletRequest request){
        model.addAttribute("userRegistrationDto", user);
        User userExists = userRepository.findUserByUsername(user.getUsername());
        System.out.println(userExists);
        Role role= roleRepository.findByName("CUSTOMER");
        if (userExists != null) {
            return "redirect:/User/register?username";
        }
        if(result.hasErrors()){
            return "User/auth/register";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setRole(role);
        role.setUser(user);
        userRepository.save(user);
        Verification verified = new Verification(user.getEmail());
        service.sendSimpleEmail(user.getEmail(),request.getRequestURL().toString()+"/verification/"+verified.getToken(),"please verified Email");
        verifiedRepository.save(verified);
        roleRepository.save(role);
        return "redirect:/User/login";
    }

    @GetMapping("/User/login")
    public String loginAdmin(){
        return "User/auth/login";
    }

    @PostMapping ("/User/login")
    public String loginpostAdmin(@ModelAttribute User user){
       User usertest= userRepository.findUserByUsername(user.getUsername());

       if(usertest!=null){
        String x =  (user.getPassword());
           String y =  (usertest.getPassword());
           System.out.println("--------------------------------------");
           System.out.println(x);
           System.out.println(y);
           if(BCrypt.checkpw(x, y)){
               Authentication authentication = new UsernamePasswordAuthenticationToken(usertest, null, usertest.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authentication);
               return "redirect:/";
           }
           else{
               return "redirect:/User/login?error=wrongpass";
           }
       }
       else{
           return "redirect:/User/login?error=notexist";
       }
    }



    @GetMapping("/User/register/verification/{token}")
    public String verificationEmail(Model m, @PathVariable String token){
        Verification verified = verifiedRepository.findVerificationByToken(token);
        if (verified==null){
            return "error/404";
        }
        User user = userRepository.findUserByEmail(verified.getUserEmail());
        user.setVerified(true);
        userRepository.save(user);
        return "User/auth/login";
    }

    @GetMapping("/User/appointment")
    public String getAppointment(Model model){
      List<WorkShop> shops= workShopRepository.findAll();
        System.out.println(shops+"555555555555555555555");
        model.addAttribute("Shops",shops);
        return "/appointment/appointment";
    }


}