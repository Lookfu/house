package com.example.house.service.impl;

import com.example.house.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public boolean sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);
        javaMailSender.send(simpleMailMessage);
        return true;
    }

    @Override
    public boolean sendHtmlMail(String to, String title, String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(content, true);
        javaMailSender.send(message);
        return true;
    }

    @Override
    public boolean sendAttachMail(String to, String title, String content, String filePath) throws MessagingException {
        MimeMessage mineMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mineMessage, true);
        // ???????????????
        mimeMessageHelper.setFrom(from);
        // ???????????????
        mimeMessageHelper.setTo(to);
        // ????????????
        mimeMessageHelper.setSubject(title);
        // ????????????
        mimeMessageHelper.setText(content);
        // ????????????
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        mimeMessageHelper.addAttachment(fileName, file);
        // ??????????????????
        //mimeMessageHelper.addAttachment(fileName, file);
        //mimeMessageHelper.addAttachment(fileName, file);
        // ?????? api, ????????????
        javaMailSender.send(mineMessage);
        return true;
    }

    @Override
    public boolean sendActivateLinkMail(String to, String link) throws MessagingException {
        Context context = new Context();
        context.setVariable("link", link);
        String emailContent = templateEngine.process("activateLink", context);
        sendHtmlMail(to, "??????????????????", emailContent);
        return true;
    }
}
