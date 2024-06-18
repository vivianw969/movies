package org.example.practice.service;

import org.example.practice.entity.Movie;
import org.example.practice.entity.User;
import org.example.practice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender emailSender;

    public void sendNotification(Movie movie, String action) {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            String email = user.getEmail();
            String subject = "Movie " + action;
            String message = "Movie " + action + ": " + movie.getTitle();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("yujiajia969@163.com");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            try {
                emailSender.send(mailMessage);
                System.out.println("Email sent to: " + email);  // Add this line for logging
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to send email to: " + email);  // Add this line for logging
            }
        }
    }
}
