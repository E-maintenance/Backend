package runtimeTerror.autoCare.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.model.blog.Review;
import runtimeTerror.autoCare.model.blog.WorkshopReview;
import runtimeTerror.autoCare.repository.UserRepository;
import runtimeTerror.autoCare.repository.WorkShopRepository;
import runtimeTerror.autoCare.repository.blog.WorkshopRevRepository;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Controller
public class WorkShopRevController {

    @Autowired
    WorkshopRevRepository workshopRevRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkShopRepository workShopRepository;

    @GetMapping("/workshopRev/{id}")
    public String getWorkshopReviews(Principal principal, Model model,@PathVariable Long id) {
        User user = userRepository.findUserByUsername(principal.getName());
//         WorkShop workShop = workShopRepository.findWorkShopById(id);
        List<WorkshopReview> workshopReviews = workshopRevRepository.findAllByUser_Username(user.getUsername()).orElseThrow();
        model.addAttribute("workshopReviews", workshopReviews);
        model.addAttribute("principal", principal.getName());
        return "/blog/workshopRev";
    }

    @PostMapping("/workshopRev/{id}")
    public String createWorkshopRev (@ModelAttribute WorkshopReview workshopReviews,@PathVariable Long id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByUsername(userDetails.getUsername());
       WorkShop workShop= workShopRepository.findWorkShopById(id);
        workShop.setRate(Double.parseDouble(workshopReviews.getRate()));
        workShopRepository.save(workShop);
        workshopReviews.setUser(user);
        workshopRevRepository.save(workshopReviews);
        userRepository.save(user);
        user.setWorkshopReview(Collections.singletonList(workshopReviews));
        return ("redirect:/workShopProfile/{id}");
    }


//    @PostMapping("/workshopRev/{id}")
//    public RedirectView createWorkshopRev(@PathVariable("id") Long id, Principal principal, Model model, String body) throws ParseException {
//        User user = userRepository.findUserByUsername(principal.getName());
//        WorkShop workShop = workShopRepository.findWorkShopById(id);
//        if(user != null){
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//            sdf.format(timestamp);
//            WorkshopReview workshopReview = new WorkshopReview(body, timestamp, user);
//            workshopRevRepository.save(workshopReview);
//        }
//
//        model.addAttribute("principal", principal.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("workshopRev" , user.getWorkshopReview());
//        model.addAttribute("workShop", workShop);
//        return new RedirectView("/workshopRev");
//    }

    @GetMapping("/addWorkshopRev/{id}")
    public String getAddWorkshopRevPage( @PathVariable Long id , Model model, Principal principal) {
        User user =  userRepository.findUserByUsername(principal.getName());
        WorkShop workShop = workShopRepository.findWorkShopById(id);
        model.addAttribute("user",user);
        model.addAttribute("workShop",workShop);

        return "/blog/addWorkshopRev";
    }

    @GetMapping("/deleteWorkshopRev/{id}")
    public String deleteWorkshopReview(@PathVariable("id") Long id, Model model){
        WorkshopReview workshopReview = workshopRevRepository.findWorkshopReviewById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + id));
        workshopRevRepository.deleteById(id);
        return "redirect:/workshopRev";
    }
}
