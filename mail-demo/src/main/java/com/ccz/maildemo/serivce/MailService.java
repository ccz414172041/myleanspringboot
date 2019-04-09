package com.ccz.maildemo.serivce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.TreeMap;

@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String fromMail;

    /**
     * 邮件（发送html）
     * @param toMail 收件人
     * @param subject 邮件标题
     * @param content 内容
     */
    public void sendEmailHTML(String toMail, String subject, String content) {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setTo(toMail);
            messageHelper.setFrom(fromMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            sender.send(mimeMessage);
            log.info("邮件由" + fromMail + "发送给" + toMail);
            log.info("主题：" + subject);
            log.info("内容：" + content);
        } catch (MessagingException e) {
            log.error("发送给" + toMail + "html send mail error subject：" + subject);
            e.printStackTrace();
        }
    }

    public void sendEmailTemplate(){

    }

    /**
     * 发送附件邮件
     * @param toMail 收件人
     * @param subject 邮件标题
     * @param content 内容
     * @param filePath 附件地址
     */
    public void sendAttachmentEmail(String toMail, String subject, String content,String filePath){
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setTo(toMail);
            messageHelper.setFrom(fromMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            messageHelper.addAttachment(fileName,fileSystemResource);
            sender.send(message);
            log.info("发送成功！");
        } catch (MessagingException e) {
            log.error("发送给" + toMail + "html send mail error subject：" + subject);
            e.printStackTrace();
        }
    }

}
