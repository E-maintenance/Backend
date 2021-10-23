package runtimeTerror.autoCare.controller.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;




    @Service
    public class EmailServiceImpl  {

        @Autowired
        private JavaMailSender mailSender;

        public void sendSimpleEmail(String toEmail,
                                    String body,
                                    String subject) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("deyaa.test@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
        }
    }
