package com.techlabs.email.service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techlabs.email.entity.EmailDetails;
 

@Service
public class EmailServiceImpl implements EmailService {
 
    @Autowired 
    private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") 
    private String sender;
 
    @Override
    public String sendSimpleMail(EmailDetails details)
    {
 
        try {
 
     
            SimpleMailMessage mailMessage = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 

        catch (Exception e) {
        	return "Error while Sending Mail";
        }
    }
 

    @Override
    public String sendMailWithAttachment(EmailDetails details)
    {

        jakarta.mail.internet.MimeMessage mimeMessage= (jakarta.mail.internet.MimeMessage) javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
 
		try {
			mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(
			    details.getSubject());
		} catch (jakarta.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
 
		try {
			mimeMessageHelper.addAttachment(
			    file.getFilename(), file);
		} catch (jakarta.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 

		javaMailSender.send(mimeMessage);
		return "Mail sent Successfully";
    }
}