package com.demo.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Admin on 2016/11/3.
 */
public class MailUtil {
    private static String username = "huang.finn@163.com";
    private static String password = "Hh123456";
    private static String smtpServer = "smtp.163.com";//邮件协议
    private static String fromMailAddress = "huang.finn@163.com";
    private static String toMailAddress = "47965866@qq.com";

    public void SendMail(String mailBody,String mailAddress) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpServer);
        // 获得邮件会话对象
        Session session = Session.getDefaultInstance(props,
                new SmtpAuthenticator(username, password));
        /** *************************************************** */
        // 创建MIME邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(fromMailAddress));// 发件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
                mailAddress));// 收件人
        mimeMessage.setSubject("会议");
        mimeMessage.setSentDate(new Date());// 发送日期
        BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
       // mdp.setText("User for Debug");// 设置纯文本形式
//        mdp.setContent( "<body><font color='red'><h3>"+mailBody+"</h3></font></div></body>",
//                "text/html;charset=UTF-8");// 设置发送邮件内容为HTML类型,并为中文编码
        mdp.setContent(mailBody,"text/html;charset=utf-8");
        Multipart mm = new MimeMultipart();
        mm.addBodyPart(mdp);
        mimeMessage.setContent(mm);
        mimeMessage.saveChanges();
        Transport.send(mimeMessage);// 发送邮件
    }
}
class SmtpAuthenticator extends Authenticator {
    String username = null;
    String password = null;

    // SMTP身份验证
    public SmtpAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.username, this.password);
    }
}

