package com.sachindrarodrigo.express_delivery_server.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String email, String body) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        //Body of email. In HTML format including CSS
        String htmlMsg = "<html>\n" +
                "  <head>\n" +
                "    <style>\n" +
                "      table,\n" +
                "      th,\n" +
                "      td {\n" +
                "        border: 1px solid black;\n" +
                "      }\n" +
                "\n" +
                "      table {\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      th {\n" +
                "        width: 30%;\n" +
                "        text-align: left;\n" +
                "        padding: 10px;\n" +
                "      }\n" +
                "\n" +
                "      .ln {\n" +
                "          color: red;\n" +
                "      }\n" +
                "\n" +
                "      td {\n" +
                "        padding-left: 10px;\n" +
                "      }\n" +
                "\n" +
                "      .img-license {\n" +
                "        width: 500px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h2>Creation of Driver account</h2>\n" +
                "\n" +
                "    <table>\n" +
                "      <tr>\n" +
                "        <th>Body</th>\n" +
                "        <td>"+body+"</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </body>\n" +
                "</html>";
        try {

            // Set other attributes
            helper.setText(htmlMsg, true); // Use this or above line.
            helper.setTo(email);
            helper.setSubject("Express Delivery Notification Service");
            helper.setFrom("doorstepdeliverieslk@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }

        // Send email
        emailSender.send(mimeMessage);
    }
}
