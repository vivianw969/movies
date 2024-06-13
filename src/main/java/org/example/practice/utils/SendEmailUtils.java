package org.example.practice.utils;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
@Slf4j
public class SendEmailUtils {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String userName;

    /**
     * 发送邮箱方法
     * @param title 标题
     * @param html 发送的html内容
     * @param message 发送的信息
     */
    public void sendHtml(String title, String html, String message) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        //需要借助Helper类
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        try {
            helper.setFrom(userName);  // 必填
            helper.setTo(message);   // 必填
//            helper.setBcc("密送人");   // 选填
            helper.setSubject(title);  // 必填
            helper.setSentDate(new Date());//发送时间
            helper.setText(html, true);   // 必填  第一个参数要发送的内容，第二个参数是不是Html格式。
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            log.error("发送邮件失败", e);
            throw new RuntimeException(e);
        }
    }

}