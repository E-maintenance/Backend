package runtimeTerror.autoCare.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import runtimeTerror.autoCare.model.*;
import runtimeTerror.autoCare.repository.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class WorkShopController {

    @Autowired
    WorkShopRepository workShopRepository;

    @Autowired
    WorkShopFeedsRepository workShopFeedsRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/shop-signup")
    public String signUp() {
        return "workShop/loginSignup";
    }

    @PostMapping("/shop-signup")
    public String attemptSignUp(@ModelAttribute WorkShop workShop ,@ModelAttribute Location loc) {
        System.out.println("--------------------------------------------------------------");
//        System.out.println(workShop);
//        System.out.println(loc);
        
        locationRepository.save(loc);
        workShop.setPassword(passwordEncoder.encode(workShop.getPassword()));
        workShop.setLocation(loc);
        loc.setWorkShop(workShop);


        workShopRepository.save(workShop);

        return ("redirect:/shop-signup#");
    }

    @GetMapping("/shop-signup#")
    public String login() {
        return "workShop/loginSignup";
    }

    @GetMapping("/appointment-control")
    public String getAppointment() {
        return "/appointment/appointmentControl";
    }





    @PostMapping ("/shop-login")
    public String LoginPage(@RequestParam String username) {
        WorkShop workShop = workShopRepository.findWorkShopByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(workShop, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ("redirect:/workShopProfile");
    }


    @GetMapping("/workShopProfile")
    public String viewProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findAllByWorkShop_Id(workShop.getId()).orElseThrow();
        model.addAttribute("workShop", workShop);
        model.addAttribute("workShopFeeds", workShopFeeds);
        return "workShop/workShopProfile";
    }


    @GetMapping("/workShopProfile/{id}")
    public String getProfile(Model model , @PathVariable Long id , Principal principal) {
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findAllByWorkShop_Id(id).orElseThrow();
        WorkShop proShop = workShopRepository.findWorkShopById(id);
        User user = userRepository.findUserByUsername(principal.getName());
        model.addAttribute("shop_Id", proShop.getId());
        model.addAttribute("user_Id",user.getId());
model.addAttribute("workShop",proShop);
//        model.addAttribute("workShopFeeds", workShop.getFeeds());
        model.addAttribute("workShopFeeds", workShopFeeds);
        return "workShop/userWorkShopProfile";
    }



    @GetMapping("/feeds")
    public String getFeeds(@ModelAttribute WorkShopFeeds workShopFeed, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findAllByWorkShop_Username(userDetails.getUsername()).orElseThrow();
        model.addAttribute("workShopFeeds", workShopFeeds);
        return "workShop/feeds";
    }

    @PostMapping("/feeds")
    public String shopFeeds(@ModelAttribute WorkShopFeeds feeds) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        feeds.setWorkShop(workShop);
        workShopFeedsRepository.save(feeds);
        workShopRepository.save(workShop);
        workShop.setFeeds(Collections.singletonList(feeds));
        return ("redirect:/workShopProfile");
    }

    @GetMapping("/shop-edit/{id}")
    public String getForm(@PathVariable Long id, Model model ){
//        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));

        WorkShopFeeds workShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id).orElseThrow();
        System.out.println(workShopFeeds.getId());
        model.addAttribute("workShopFeeds", workShopFeeds );
        return "workShop/updateFeed";
    }

    @PostMapping("/shop-update/{id}")
    public String updateForm(@PathVariable("id") Long id , WorkShopFeeds workShopFeeds,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            workShopFeeds.setId(id);
            return "workShop/feeds";
        }
        WorkShopFeeds oldWorkShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id).orElseThrow();
        oldWorkShopFeeds.setFeeds(workShopFeeds.getFeeds());
        oldWorkShopFeeds.setImage(workShopFeeds.getImage());
        oldWorkShopFeeds.setTimeNow(new Date().getTime());

        workShopFeedsRepository.save(oldWorkShopFeeds);
        return "redirect:/workShopProfile";
    }

    @GetMapping("/shop-delete/{id}")
    public String deleteFeed(@PathVariable("id") Long id, Model model){
        WorkShopFeeds workShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));
        workShopFeedsRepository.deleteById(id);
        return "redirect:/workShopProfile";
    }



}