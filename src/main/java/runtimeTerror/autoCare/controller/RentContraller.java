package runtimeTerror.autoCare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.model.Rent;
import runtimeTerror.autoCare.repository.RentRepository;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class RentContraller {

    @Autowired
    RentRepository rentrepo;

    @GetMapping("/card/{id}")
    public String paymentCard(Principal principal , Model model) {
        model.addAttribute("user", rentrepo.findAll());
        return "rentnow";

}
    @PostMapping("/payment")
    public RedirectView payment(@ModelAttribute Rent rent) {
        Rent newUser = new Rent(rent.getName(),rent.getCardnumber(),rent.getDate(),rent.getFirstname(),rent.getSecondename()
        ,rent.getEmail(),rent.getPhonnumber(),rent.getAddress(),rent.getNationalityID(),rent.getDOB());
        rentrepo.save(newUser);
//        System.out.println(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/detales");
    }
    @GetMapping("/detales")
    public String paymentCard2(Principal principal, Model model) {
        model.addAttribute("username", rentrepo.findAll());

        return "rentnow";

    }
    @GetMapping("/delete/{id}")
    String delete(@PathVariable("id") Long id ,Model model){
        Rent rent =rentrepo.getById(id);
        rentrepo.deleteById(id);
        return "redirect:/detales";
    }


}
