package runtimeTerror.autoCare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import runtimeTerror.autoCare.repository.WorkShopRepository;

@Controller
public class WorkShopController {

    @Autowired
    WorkShopRepository workShopRepository;


    @GetMapping("/")
    public String goHome() {
        return "home";
    }

}
