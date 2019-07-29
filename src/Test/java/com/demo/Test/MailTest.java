package com.demo.Test;


import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Admin on 2016/11/3.
 */
public class MailTest {
    private static String username = "huang.finn@163.com";
    private static String password = "Hh123456";
    private static String smtpServer = "smtp.163.com";//邮件协议
    private static String fromMailAddress = "huang.finn@163.com";
    private static String toMailAddress = "47965866@qq.com";
    @Test
    public void SendMail() throws Exception{
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
                toMailAddress));// 收件人
        mimeMessage.setSubject("会议");
        mimeMessage.setSentDate(new Date());// 发送日期
        BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
        mdp.setText("User for Debug");// 设置纯文本形式
        String mailBody="why";
        mdp.setContent( "<body><font color='red'><h3>"+mailBody+"</h3></font></div></body>",
                "text/html;charset=UTF-8");// 设置发送邮件内容为HTML类型,并为中文编码
        Multipart mm = new MimeMultipart();
        mm.addBodyPart(mdp);
        mimeMessage.setContent(mm);
        mimeMessage.saveChanges();
        Transport.send(mimeMessage);// 发送邮件
    }


    public static void sendMail(String fromMail, String user, String password,
                                String toMail,
                                String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties(); //可以加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", smtpServer);//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证

        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        session.setDebug(true); //有他会打印一些调试信息。

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
        message.setSubject(mailTitle);//设置标题
        //设置信件内容
      message.setText(mailContent); //发送 纯文本 邮件 todo
     //   message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
Transport transport = session.getTransport("smtp");
      //  Transport transport = session.getTransport();
        transport.connect(user, password);
     //   transport.sendMessage(message,);//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
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
