package runtimeTerror.autoCare.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class Service {

    @GetMapping("/service")
    public String ser() {
        return "servicepage";
    }

    @GetMapping("/rent")
    public String rent() {
        return "NowRent";
    }
    @GetMapping("/rentnow")
    public String rentcar() {
        return "rentnow";
    }

}
