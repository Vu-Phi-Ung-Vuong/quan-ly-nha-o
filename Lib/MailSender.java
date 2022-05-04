/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lib;

import java.util.ArrayList;
import java.util.List;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MailSender extends Thread{
    static {
        MailSender sender = new MailSender();
        sender.start();
    }
    static final List<MimeMessage> queue= new ArrayList<>();
    public static boolean b=false;
    public static void queue(MimeMessage mail){
        synchronized(queue){
            queue.add(mail);
            queue.notify();
        }
    }
    public static boolean isSend(){        
        while (b==false) {
            System.out.print("");
        }
        return b;
    }
    @Override
    public void run() {
        while (true) {            
            try {
                synchronized(queue){
                    if(queue.size()>0){
                        b=false;
                        try {
                            MimeMessage mail= queue.remove(0);
                            Transport.send(mail);
                            System.out.println("đã gửi");
                            b=true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("không gửi được");
                        }
                    }
                    else{
                        queue.wait();
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
    }
    
}
