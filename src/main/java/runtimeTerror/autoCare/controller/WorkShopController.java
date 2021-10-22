package runtimeTerror.autoCare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.repository.WorkShopFeedsRepository;
import runtimeTerror.autoCare.repository.WorkShopRepository;

@Controller
public class WorkShopController {

    @Autowired
    WorkShopRepository workShopRepository;

    @Autowired
    WorkShopFeedsRepository workShopFeedsRepository;


    @GetMapping("/shopProfile")
    public String viewProfile(){
        return "shopProfile";
    }
}
