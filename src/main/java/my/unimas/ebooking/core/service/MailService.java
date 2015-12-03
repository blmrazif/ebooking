package my.unimas.ebooking.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by RazifBaital on 7/28/2015.
 */
@Service("mailService")
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        // message.setCc("irisadm@unimas.my");
        mailSender.send(message);
    }

}
