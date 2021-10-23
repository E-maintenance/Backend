package runtimeTerror.autoCare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String viewProfile(@PathVariable String username, Model model) {

        WorkShop workShop = workShopRepository.findWorkShopByUsername(username);
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findWorkShopById(workShop.getId()).orElseThrow();
        model.addAttribute("workshop", workShop);
        model.addAttribute("feeds", feeds);
        return "shopProfile";
    }

    @GetMapping("/feeds")
    public String getFeeds(@PathVariable String username, Model model) {
        List<WorkShopFeeds> feeds = workShopFeedsRepository.findAllByWorkShop_Username(username).orElseThrow();
        model.addAttribute("feeds", feeds);
        return "feeds";
    }

    @PostMapping("/feeds")
    public RedirectView shopFeeds(@PathVariable String username, @ModelAttribute WorkShopFeeds feeds) {
        WorkShop workShop = workShopRepository.findWorkShopByUsername(username);
        feeds.setWorkShop(workShop);
        workShopRepository.save(workShop);
        workShopFeedsRepository.save(feeds);
        workShop.setFeeds(Collections.singletonList(feeds));
        return new RedirectView("/shopProfile");
    }

    @GetMapping("/edit/{id}")
    public String getForm(@PathVariable Long id, Model model ){
        List<WorkShopFeeds> workShopFeeds = workShopFeedsRepository.findWorkShopById(id).orElseThrow();
        model.addAttribute("workshopFeeds", workShopFeeds );
        return "feeds";
    }
    @PostMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id , @ModelAttribute WorkShopFeeds workShopFeeds){
        workShopFeedsRepository.save(workShopFeeds);
        return "redirect:/shopProfile";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeed(@PathVariable("id") Long id,Model model){
        List<WorkShopFeeds> workShopFeeds= workShopFeedsRepository.findWorkShopById(id).orElseThrow();
        workShopFeedsRepository.deleteWorkShopById(id);
        return "redirect:/shopProfile";
    }

}