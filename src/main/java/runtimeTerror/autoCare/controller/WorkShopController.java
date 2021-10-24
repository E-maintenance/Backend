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
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.repository.WorkShopFeedsRepository;
import runtimeTerror.autoCare.repository.WorkShopRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WorkShopController {

    @Autowired
    WorkShopRepository workShopRepository;

    @Autowired
    WorkShopFeedsRepository workShopFeedsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @GetMapping("/shop-signup")
    public String signUp() {
        return "workShop/loginSignup";
    }

    @PostMapping("/shop-signup")
    public String attemptSignUp(WorkShop workShop) {
        workShop.setPassword(passwordEncoder.encode(workShop.getPassword()));
        workShopRepository.save(workShop);
        return ("redirect:/shop-signup#");
    }

    @GetMapping("/shop-signup#")
    public String login() {
        return "workShop/loginSignup";
    }

    @PostMapping ("/shop-login")
    public String LoginPage(@RequestParam String username) {
        WorkShop workShop = workShopRepository.findWorkShopByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(workShop, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ("redirect:/shopProfile");
    }


    @GetMapping("/shopProfile")
    public String viewProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findWorkShopFeedsById(workShop.getId()).orElseThrow();
        model.addAttribute("workShop", workShop);
        model.addAttribute("feeds", feeds);
        return "workShop/workShopProfile";
    }

    @GetMapping("/feeds")
    public String getFeeds(@ModelAttribute WorkShopFeeds workShopFeeds, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findAllByWorkShop_Username(userDetails.getUsername()).orElseThrow();
        model.addAttribute("feeds", feeds);
        return "workShop/feeds";
    }

    @PostMapping("/feeds")
    public String shopFeeds(@ModelAttribute WorkShopFeeds feeds) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        feeds.setWorkShop(workShop);
        workShopRepository.save(workShop);
        workShopFeedsRepository.save(feeds);
        workShop.setFeeds(Collections.singletonList(feeds));
        return ("redirect:/shopProfile");
    }

    @GetMapping("/shop-edit/{id}")
    public String getForm(@PathVariable Long id, Model model ){
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));
        model.addAttribute("workshop", workShopFeeds );
        return "/workShop/updateFeed";
    }

    @PostMapping("/shop-update/{id}")
    public String updateForm(@PathVariable("id") Long id , WorkShopFeeds workShopFeeds,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            workShopFeeds.setId(id);
            return "workShop/feeds";
        }
        workShopFeedsRepository.save(workShopFeeds);
        return "redirect:/shopProfile";
    }

    @GetMapping("/shop-delete/{id}")
    public String deleteFeed(@PathVariable("id") Long id, Model model){
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findWorkShopFeedsById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));
        workShopFeedsRepository.deleteWorkShopFeedsById(id).orElseThrow();
        return "redirect:/shopProfile";
    }

}