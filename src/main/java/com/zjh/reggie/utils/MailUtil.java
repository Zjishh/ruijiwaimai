package com.zjh.reggie.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Date;

/****************************
 * @project reggie
 * @package com.zjh.reggie.service
 * @className MailService
 * @author Zjiah
 * @date 2023/10/25 9:06
 * @Description:   *
 ****************************/
@Slf4j
@Service
public class MailUtil {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    public void sendTextMailMessage(String to,String subject,String text){


        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功："+sendMailer+"->"+to);
        } catch (MessagingException e) {
            log.info("发送失败！！！"+e.getMessage());

            throw new RuntimeException(e);
        }


    }


}
