package runtimeTerror.autoCare.controller.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import runtimeTerror.autoCare.model.Appointment;
import runtimeTerror.autoCare.model.User;
import runtimeTerror.autoCare.model.WorkShop;
import runtimeTerror.autoCare.repository.*;

import java.security.Principal;

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

//    @GetMapping("/accepted")
//    public String getmakeAppointmantd () {
//        return "/appointment/accepted";
//    }



}
