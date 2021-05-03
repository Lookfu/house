package com.example.house.service;

import javax.mail.MessagingException;

public interface MailService {
    boolean sendSimpleMail(String to, String title, String content);

    boolean sendHtmlMail(String to, String title, String content) throws MessagingException;

    boolean sendAttachMail(String to, String title, String content, String filePath) throws MessagingException;

    boolean sendActivateLinkMail(String to, String link) throws MessagingException;
}
