package runtimeTerror.autoCare.controller.ContactUs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import runtimeTerror.autoCare.model.ContactUs.ContactUsModel;
import runtimeTerror.autoCare.model.ContactUs.HelloMessage;
import runtimeTerror.autoCare.repository.ContactUs.ContactUsRep;
import runtimeTerror.autoCare.repository.ContactUs.HelloMessageRepository;

import javax.websocket.server.ServerEndpoint;
import java.net.URISyntaxException;
@ServerEndpoint("/endpoint")
@Controller
public class ContactUsCont {
    @Autowired
    ContactUsRep ContactUs;
    @Autowired
    HelloMessageRepository helloMessageRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/contacts")
    public ContactUsModel contact(HelloMessage message) throws InterruptedException, URISyntaxException {

        helloMessageRepository.save(message);
        Thread.sleep(1000); // simulated delay

        ContactUsModel greeting = new ContactUsModel("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        ContactUs.save(greeting);
        return greeting;
    }
//    @GetMapping("/contact")
//    public String contact(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }
}
