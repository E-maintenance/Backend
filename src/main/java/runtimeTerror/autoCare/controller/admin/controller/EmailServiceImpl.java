package runtimeTerror.autoCare.controller.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


    @Service
    public class EmailServiceImpl  {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String body, String subject) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("deyaa.test@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            mailSender.send(message);
        }
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("auto_care@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }


    public String buildEmail(String name, String link) {
        return
                "<div style=\"margin-top: 100px; border: 18px solid #f4f4f4; border-radius: 25px; \">"
                +"\n"+
                "<div style=\" height: 100px; background-color: #f4f4f4; padding: 18px; border-bottom: 1px solid #dcd5d5;\">"
                +"\n"+
                "<img src="+"https://lh3.google.com/u/0/d/1rmF0ASfrv-v69x5a-D4bTRhjLEQsseYl=w1366-h412-iv1"+"  style=\"float: left; height: 100px; width: 30%;\">"
                +"\n"+
                "<img src="+"https://www.pngkit.com/png/full/67-670301_cars-png-logo-used-car-sales-logo.png"+" style=\"float: right; height: 100px; width: 30%;\">"
                +"\n"+
                "</div>"
                +"\n"+
                "<div style=\"position: relative; height: auto; background-color: #f4f4f4; padding: 25px;text-align: center; display: block;\">"
                +"\n"+
                "<h1 style=\"color: #626262; top:0; left: 0;  text-align:left; margin: 20px;\">dear  "+name+"</h1>"
                +"\n"+
                "<h2 style=\"color: #626262; \">We are excited to have you join the Auto-Care Unified Product System family </h2>"
                +"\n"+
                "<h2 style=\"color: #626262; \"> To activate your account, please visit this link</h2>"
                +"\n"+
                "<a href="+link+"> <button style=\"background-color: #4CAF50;  border: none;color: white;text-align: center;text-decoration: none;display: inline-block;font-size: 30px;margin: 4px 2px;cursor: pointer; padding: 7px 50px; border-radius: 10px;\">activate account</button></a>"
                +"\n"+
                "<h2 style=\"color: #626262; \">If the link is not activated, you can use this code</h2>"
                +"\n"+
                "<h4 style=\"color: #626262; \">This link is valid for 5 days only and will be deactivated</h4>"
                +"\n"+
                "<h4 style=\"color: #626262; \"> Ignore this message if you have not requested an account in the system</h4>"
                +"\n"+
                "</div>"
                +"\n"+
                "</div>";
    }
}
