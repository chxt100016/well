
package org.wella.common.mail;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
    public MailUtil() {
    }

    public static void sendMail(String toEmail, String subject, String htmlContent) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        Constants.emailFrom = "362376191@qq.com";
        Constants.emailHost = "smtp.qq.com";
        Constants.emailUsername = "362376191@qq.com";
        Constants.emailPassword = "zzqinlyj2007726#";
        senderImpl.setHost(Constants.emailHost);
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");

        try {
            messageHelper.setTo(toEmail);
        } catch (MessagingException var12) {
            throw new RuntimeException("收件人邮箱地址出错！");
        }

        try {
            messageHelper.setFrom(Constants.emailFrom);
        } catch (MessagingException var11) {
            throw new RuntimeException("发件人邮箱地址出错！");
        }

        try {
            messageHelper.setSubject(subject);
        } catch (MessagingException var10) {
            throw new RuntimeException("邮件主题出错！");
        }

        try {
            messageHelper.setText(htmlContent, true);
        } catch (MessagingException var9) {
            throw new RuntimeException("邮件内容出错！");
        }

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "25000");
        MyAuthenticator auth = new MyAuthenticator(Constants.emailUsername, Constants.emailPassword);
        Session session = Session.getDefaultInstance(prop, auth);
        senderImpl.setSession(session);
        senderImpl.send(mailMessage);
    }
}
