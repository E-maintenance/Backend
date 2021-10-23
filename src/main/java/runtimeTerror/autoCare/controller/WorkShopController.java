package runtimeTerror.autoCare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.repository.WorkShopFeedsRepository;
import runtimeTerror.autoCare.repository.WorkShopRepository;

import java.util.Collections;
import java.util.List;

@Controller
public class WorkShopController {

    @Autowired
    WorkShopRepository workShopRepository;

    @Autowired
    WorkShopFeedsRepository workShopFeedsRepository;


    @GetMapping("/shopProfile")
    public String viewProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findWorkShopById(workShop.getId()).orElseThrow();
        model.addAttribute("workshop", workShop);
        model.addAttribute("feeds", feeds);
        return "shopProfile";
    }

    @GetMapping("/feeds")
    public String getFeeds(@ModelAttribute WorkShopFeeds workShopFeeds, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findAllByWorkShop_Username(userDetails.getUsername()).orElseThrow();
        model.addAttribute("feeds", feeds);
        return "feeds";
    }

    @PostMapping("/feeds")
    public RedirectView shopFeeds(@ModelAttribute WorkShopFeeds feeds) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        WorkShop workShop = workShopRepository.findWorkShopByUsername(userDetails.getUsername());
        feeds.setWorkShop(workShop);
        workShopRepository.save(workShop);
        workShopFeedsRepository.save(feeds);
        workShop.setFeeds(Collections.singletonList(feeds));
        return new RedirectView("/shopProfile");
    }

    @GetMapping("/workshop/edit/{id}")
    public String getForm(@PathVariable Long id, Model model ){
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findWorkShopById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));
        model.addAttribute("workshopFeeds", workShopFeeds );
        return "feeds";

    }
    @PostMapping("/workshop/update/{id}")
    public String updateForm(@PathVariable("id") Long id , WorkShopFeeds workShopFeeds,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            workShopFeeds.setId(id);
            return "feeds";
        }
        workShopFeedsRepository.save(workShopFeeds);
        return "redirect:/shopProfile";
    }

    @GetMapping("/workshop/delete/{id}")
    public String deleteFeed(@PathVariable("id") Long id, Model model){
        List<WorkShopFeeds> workShopFeeds= workShopFeedsRepository.findWorkShopById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Feed Id:" + id));
        workShopFeedsRepository.deleteWorkShopById(id);
        return "redirect:/shopProfile";
    }

}