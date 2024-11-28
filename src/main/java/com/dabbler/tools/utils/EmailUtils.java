package com.dabbler.tools.utils;

import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPStore;
import jakarta.mail.*;
import jakarta.mail.internet.MimeUtility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

//https://gist.github.com/kang8/67e03361d8cf7658b1ea7c55084b2045
//https://engr-z.com/298.html
//https://www.cnblogs.com/antLaddie/p/15583991.html
public class EmailUtils {


    private EmailUtils(){
        throw new UnsupportedOperationException();
    }

    public static IMAPStore connect(Properties props) throws MessagingException {
        Session session = Session.getDefaultInstance(props);
        IMAPStore store = (IMAPStore) session.getStore("imap");
        // 需要认证
        store.connect(props.getProperty("mail.imap.host"), Integer.valueOf(props.getProperty("mail.imap.port","993")), props.getProperty("mail.imap.user"), props.getProperty("mail.imap.pass"));
        // 解决报错： https://help.mail.163.com/faqDetail.do?code=d7a5dc8471cd0c0e8b4b8f4f8e49998b374173cfe9171305fa1ce630d7f67ac2eda07326646e6eb0
        // Unsafe Login. Please contact kefu@188.com for help
        HashMap IAM = new HashMap();
        //带上IMAP ID信息，由key和value组成，例如name，version，vendor，support-email等。
        IAM.put("name","imap-client");
        IAM.put("version","1.0.0");
        IAM.put("vendor","dabbler-toolbox");
        IAM.put("support-email","testmail@xxx.com");
        store.id(IAM);
        return store;

    }

    public static Message getMailMessage(IMAPStore store) throws MessagingException, IOException {

        // 获取收件箱 store.getDefaultForlder
        Folder mbox = store.getFolder("账单");
        // INBOX
        mbox.open(Folder.READ_ONLY);
        System.out.println(mbox.getName());
        int msgCount = mbox.getMessageCount();
        System.out.println("邮件总数：" + msgCount);
        Message[] messages = mbox.getMessages();
        // 取最新的邮件
        Message msg = mbox.getMessage(msgCount);
        return msg;
    }

    public  static void parseContent(Part message) throws MessagingException, IOException {

        Object content = message.getContent();
        if (content instanceof String){
            System.out.println("文本内容：" + content);

        }else if(content instanceof Multipart){
            Multipart multipart = (Multipart)content;
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disposition = bodyPart.getDisposition();
                if (Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
                    // 处理附件
                    System.out.println(" :Attachment: " + bodyPart.getFileName());
                    InputStream is = bodyPart.getInputStream();
                } else {
                     System.out.println("ContentType: " + bodyPart.getContentType());
                    // 处理正文
                     parseContent(bodyPart);

                }
            }
        }

    }

    public static void saveAttachment(Part message,String destDir) throws MessagingException, IOException {
        Object content = message.getContent();
        if(content instanceof Multipart){
            Multipart multipart = (Multipart)content;
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disposition = bodyPart.getDisposition();
                if (Part.ATTACHMENT.equalsIgnoreCase(disposition)) {
                    // 处理附件
                    System.out.println(" :Attachment: " + bodyPart.getFileName());
                    Path dest = Paths.get(destDir);
                    if (!Files.exists(dest)){
                        Files.createDirectories(dest);
                    }
                    String fileName = MimeUtility.decodeText(bodyPart.getFileName());
                    Path destFile = Paths.get(destDir,fileName);
                    if (!Files.exists(destFile)){
                        Files.createFile(destFile);
                    }else{

                    }
                    Files.copy(bodyPart.getInputStream(),destFile, StandardCopyOption.REPLACE_EXISTING);
                }else{
                    saveAttachment(bodyPart,destDir);
                }
            }
        }
    }


    public void close( IMAPStore store) throws MessagingException {
        store.close();
    }
}
