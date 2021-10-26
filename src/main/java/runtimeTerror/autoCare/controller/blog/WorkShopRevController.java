package runtimeTerror.autoCare.controller.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class WorkShopRevController {

    @Autowired
    WorkshopRevRepository workshopRevRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkShopRepository workShopRepository;

    @GetMapping("/workshopRev")
    public String getMyProfilePage(Principal principal, Model model) {
        User user = userRepository.findUserByUsername(principal.getName());
//        WorkShop workShop = workShopRepository.findWorkShopById(id);
        model.addAttribute("user",user);
//        model.addAttribute("workShop", workShop);
        model.addAttribute("principal", principal.getName());
        return "/blog/workshopRev";
    }


    @PostMapping("/workshopRev/{id}")
    public RedirectView createWorkshopRev(@PathVariable("id") Long id, Principal principal, Model model, String body) throws ParseException {
        User user = userRepository.findUserByUsername(principal.getName());
        WorkShop workShop = workShopRepository.findWorkShopById(id);
        if(user != null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            sdf.format(timestamp);
            WorkshopReview workshopReview = new WorkshopReview(body, timestamp, user);
            workshopRevRepository.save(workshopReview);
        }

        model.addAttribute("principal", principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("workshopRev" , user.getWorkshopReview());
        model.addAttribute("workShop", workShop);
        return new RedirectView("/workshopRev");
    }

    @GetMapping("/addWorkshopRev/{id}")
    public String getAddWorkshopRevPage( @PathVariable Long id , Model model, Principal principal) {
        User user =  userRepository.findUserByUsername(principal.getName());
        WorkShop workShop = workShopRepository.findWorkShopById(id);
        model.addAttribute("user",user);
        model.addAttribute("workShop",workShop);

        return "/blog/addWorkshopRev";
    }
}
