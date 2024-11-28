package com.dabbler.tools.utils;

import com.sun.mail.imap.IMAPStore;
import jakarta.mail.Message;
import jakarta.mail.Store;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class EmailUtilsTest {

    @Test
    void connect() throws Exception {
        Properties props = new Properties();
        props.put("mail.imap.host", "imap.163.com");
        props.put("mail.imap.port", 993);
        props.put("mail.imap.ssl", true);
        // 需要认证
        props.put("mail.imap.auth", true);
        props.put("mail.imap.user", "xxxxx@163.com");
        props.put("mail.imap.pass", "zxxxxx");
        // 使用ssl
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        IMAPStore store=  EmailUtils.connect(props);
        Message message = EmailUtils.getMailMessage(store);

        EmailUtils.saveAttachment(message,"C:\\Users\\Administrator\\Desktop\\dest");
    }
}