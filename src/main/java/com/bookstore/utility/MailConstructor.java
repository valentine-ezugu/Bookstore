package com.bookstore.utility;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Created by VAlentine Ezugu on 8/5/2017.
 */
@Component
public class MailConstructor {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Environment env;

    public SimpleMailMessage constructorResetTokenEmail(
            String contextPath, Locale locale, String token, User user, String password
    ) {
        String url = contextPath + "/newUser?token=" + token;
        String message = "\nplease click on this link to verify your " +
                "email and edit your personal information. Your password is: \n" + password;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Val's Bookstore - New User");
        email.setText(url + message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale locale) {
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
        context.setVariable("cartItemList", order.getCartItemList());
        String text = templateEngine.process("orderConfirmationEmailTemplate", context);


        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setTo(user.getEmail());
                email.setSubject("Order Confirmation - " + order.getId());
                email.setText(text, true);
                email.setFrom(new InternetAddress("ezuguValentine@gmail.com"));
            }
        };
        return messagePreparator;
    }
}
