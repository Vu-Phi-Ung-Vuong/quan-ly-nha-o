/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Admin
 */
public class MailSender extends Thread {

    static {
        MailSender sender = new MailSender();
        sender.start();
    }
    public static void send(String username,String password,String title,String mess,String list) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);

            String accountName = username;  //tài khoản
            String accountPassword = password;//mật khẩu
            Session s = Session.getInstance(p,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountName, accountPassword);
                }
            });

            String from = username;  //người gửi
            String subject = title;//tiêu đề
            String body = mess;//tin nhắn
            MimeMessage msg = new MimeMessage(s);
            msg.setContent(body, "text/html; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(list));
            msg.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(body, "text/html; charset=UTF-8");
            multipart.addBodyPart(contentPart);

            msg.setContent(multipart);
            MailSender.queue(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static final List<MimeMessage> queue = new ArrayList<>();
    public static boolean b = false;

    public static void queue(MimeMessage mail) {
        synchronized (queue) {
            queue.add(mail);
            queue.notify();
        }
    }

    public static boolean isSend() {
        while (b == false) {
            System.out.print("");
        }
        return b;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    if (queue.size() > 0) {
                        b = false;
                        try {
                            MimeMessage mail = queue.remove(0);
                            Transport.send(mail);
                            System.out.println("đã gửi");
                            b = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("không gửi được");
                        }
                    } else {
                        queue.wait();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
    }
}
