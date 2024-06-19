package com.authentication.backend_authentication.email;

import com.authentication.backend_authentication.user.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async // To send Email Faster and the user can't wait for it
    public void sendEmail(
            String toEmail,
            String userName,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException
    {
        String templateName;
        if(emailTemplateName == null){
            templateName="confirm_email";
        }
        else{
            templateName=emailTemplateName.getName();
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );
        Map<String, Object> properties = new HashMap<>();
        properties.put("USERNAME",userName);
        properties.put("CONFIRMATION_URL",confirmationUrl);
        properties.put("ACTIVATION_CODE",activationCode);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);

        String template = springTemplateEngine.process(templateName, context);

        mimeMessageHelper.setText(template,true);

        javaMailSender.send(mimeMessage);
    }

}
