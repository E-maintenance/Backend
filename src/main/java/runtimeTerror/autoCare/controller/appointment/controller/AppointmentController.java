package runtimeTerror.autoCare.controller.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import runtimeTerror.autoCare.model.Appointment;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.model.WorkShopFeeds;
import runtimeTerror.autoCare.repository.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {
    @Autowired
    WorkShopRepository workShopRepository;

    @Autowired
    WorkShopFeedsRepository workShopFeedsRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/make-appointmant/{shopId}/{userId}")
    public String makeAppointmant(Model model , @PathVariable Long shopId, @PathVariable Long userId , Principal principal) {
        User userid = userRepository.findUserById(userId);
        WorkShop workShop = workShopRepository.findWorkShopById(shopId);
        Appointment appointment = new Appointment("Pending",userid,workShop);
        appointmentRepository.save(appointment);
        return "/appointment/accepted";
    }

    @GetMapping("/appointment-control")
    public String getAppointment(Model model,Principal principal){
        WorkShop workShop = workShopRepository.findWorkShopByUsername(principal.getName());
        List<Appointment>  appointment = appointmentRepository.findAllByWorkShop_Id(workShop.getId());
        System.out.println(appointment.get(0));
        model.addAttribute("appointments", appointment);

        return "/appointment/appointmentControl";
    }




    @GetMapping("/appointment-accept/{id}")
    public String updateForm(@PathVariable("id") Long id ){
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        appointment.setStatus("Accepted");
        appointmentRepository.save(appointment);
        return "redirect:/appointment-control";
    }

    @GetMapping("/appointment-cancel/{id}")
    public String deleteAppointment(@PathVariable("id") Long id){
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        appointment.setStatus("Cancel");
        appointmentRepository.save(appointment);
        return "redirect:/appointment-control";
    }

//    @GetMapping("/accepted")
//    public String getmakeAppointmantd () {
//        return "/appointment/accepted";
//    }



}
