package com.cdutcm.party.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class SendEmailByQQ {
    private static Session session = null;
    private static String from = null;
    private static String password = null;
    private static MimeMessage message = null;

    private static void getInfo(String to, String subject) {
        // 获取系统属性
        Properties properties = new Properties();
        try {
            properties.load(SendEmailByQQ.class.getClassLoader().getResourceAsStream("static/mail.properties"));
            from = properties.getProperty("mail.username");
            password = properties.getProperty("mail.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取默认session对象
        session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password); //发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: 头部头字段
            message.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static boolean SendEmail(String to, String subject, String content) {
        getInfo(to, subject);
        try {
            // 设置消息体
            message.setContent(content, "text/html;charset=utf-8");
            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean SendEmail(String to, String subject, String content, String filename) {
        getInfo(to, subject);
        try {
            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setContent(content, "text/html");
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();
            URL url = SendEmailByQQ.class.getClassLoader().getResource(filename);
            DataSource source = new FileDataSource(URLDecoder.decode(url.getFile(), "UTF-8"));
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename.substring(filename.lastIndexOf("/")));
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            //   发送消息
            Transport.send(message);
            System.out.println("Sent file email successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        // 收件人电子邮箱
        String to = "schoolbus.cdutcm@foxmail.com";
        String subject = "This is the Subject Line!";
        String text = "This is actual message";
        String content = "<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:40px\"><div class=\"x_mmsgLetter\" style=\"width:580px;margin:0px auto;padding:10px;color:rgb(51,51,51);background-color:rgb(255,255,255);border:0px solid rgb(170,170,170);border-radius:5px;font-family:Verdana,sans-serif,serif,EmojiFont;\"><div class=\"x_mmsgLetterHeader\" style=\"height:23px;background:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg_topline.png) repeat-x 0 0\"></div><div class=\"x_mmsgLetterContent\" style=\"text-align:left;padding:30px;font-size:14px;line-height:1.5;background:url(http://cdutcm.edu.cn/images/index_top_1.gif) no-repeat top right;background-size:40% 17%;\"><div><p>您好!</p><p>感谢您使用成都中医药大学校车预约系统 <br>您正在找回您的密码。请回填如下6位验证码： </p><p class=\"x_mmsgLetterDigital\" style=\"font-size:22px;letter-spacing:10px;\">123456</p><p>验证码在30分钟内有效，30分钟后需要重新发送验证码 </p></div><div class=\"x_mmsgLetterInscribe\" style=\"padding:40px 0 0\"><img data-imagetype=\"External\" src=\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=328327349,855323749&fm=27&gp=0.jpg\" class=\"x_mmsgAvatar\" style=\"float:left\" height=\"45px\" width=\"45px\"> <div class=\"x_mmsgSender\" style=\"margin:0 0 0 54px\"><p class=\"x_mmsgName\" style=\"margin:0 0 10px\">TYK</p><p class=\"x_mmsgInfo\" style=\"font-size:12px;margin:0;line-height:1.2\">开发人员<br><a href=\"mailto:766564616@qq.com\" target=\"_blank\" rel=\"noopener noreferrer\" style=\"color:#407700\">766564616@qq.com</a> </p></div></div></div><div class=\"x_mmsgLetterFooter\" style=\"margin:16px;text-align:center;font-size:12px;color:#888\"><img data-imagetype=\"External\" data-imageerror=\"RelWithoutBase\" originalsrc=\"/cgi-bin/readtemplate?sid=$SID$&amp;loc=pushmail1,weixiniphone,show,44\" src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAEALAAAAAABAAEAAAIBTAA7\" onload=\"InlineImageLoader.GetLoader().Load(this)\" style=\"width:1px;height:1px\" crossorigin=\"anonymous\"> <img data-imagetype=\"External\" src=\"http://weixin.qq.com/cgi-bin/reportforpromote?uin=$RCPTUIN$&amp;sdate=$SDATE$&amp;tver=$TVER$\" style=\"width:1px;height:1px\"> </div></div></div>";
        String filename = "邮箱.txt";

//        System.out.println(SendEmail(to,subject,text));
        System.out.println(SendEmail(to, subject, content));
//        System.out.println(SendEmail(to,subject,text,filename));
//        System.out.println(SendEmail(to,subject,content,filename));
    }
}