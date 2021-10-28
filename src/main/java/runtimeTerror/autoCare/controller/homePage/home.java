
package runtimeTerror.autoCare.controller.homePage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {

    @GetMapping("/")
    public String getHome() {
        return "/home/index";
    }


}