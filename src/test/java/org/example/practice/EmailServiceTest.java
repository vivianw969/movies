package org.example.practice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private JavaMailSender emailSender;

    @Test
    public void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yujiajia969@163.com");
        message.setTo("yujiajia969@163.com"); // Use your email for testing
        message.setSubject("Test Subject");
        message.setText("Test Email");
        emailSender.send(message);
        System.out.println("Test email sent successfully");
    }
}
