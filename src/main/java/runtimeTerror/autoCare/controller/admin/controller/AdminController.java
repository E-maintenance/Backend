package runtimeTerror.autoCare.controller.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import runtimeTerror.autoCare.dto.UserRegistrationDto;
import runtimeTerror.autoCare.model.Role;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.Verification;
import runtimeTerror.autoCare.repository.RoleRepository;
import runtimeTerror.autoCare.repository.UserRepository;
import runtimeTerror.autoCare.repository.VerifiedRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AdminController  {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerifiedRepository verifiedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired    EmailServiceImpl service;


    @GetMapping("/admin/register")
    public String registerAdmin(Model model){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto", userRegistrationDto);
        return  "admin/auth/register";
    }

    @PostMapping ("/admin/register")
    public String RegisterAdmin(@Valid @ModelAttribute User user, BindingResult result, Model model,HttpServletRequest request) throws MessagingException {
        model.addAttribute("userRegistrationDto", user);
        User userExists = userRepository.findUserByUsername(user.getUsername());
        System.out.println(userExists);
        Role role= roleRepository.findByName("ADMIN");
        if (userExists != null && (userExists.getRole().equals("1"))) {
            return "redirect:/admin/register?username";
        }
        if(result.hasErrors()){
            return "admin/auth/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        role.setUser(user);
        userRepository.save(user);
        Verification verified = new Verification(user.getEmail());
       String fun = service.buildEmail(user.getFullname(),request.getRequestURL().toString()+"/verification/"+verified.getToken());
        service.send(user.getEmail(),fun);
       verifiedRepository.save(verified);
        roleRepository.save(role);
        return "redirect:/admin/register?success";
    }

    @GetMapping("/admin/login")
    public String loginAdmin(){
        return "admin/auth/login";
    }

    @GetMapping("/admin/dashboard")
    public String index() {
        return "admin/dashboard/index";
    }

    @GetMapping("/admin/register/verification/{token}")
    public String verificationEmail(Model m, @PathVariable String token){
        Verification verified = verifiedRepository.findVerificationByToken(token);
        if (verified==null){
            return "error/404";
        }
        User user = userRepository.findUserByEmail(verified.getUserEmail());
        user.setVerified(true);
        userRepository.save(user);
        return "admin/auth/login";
    }

    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }
}
