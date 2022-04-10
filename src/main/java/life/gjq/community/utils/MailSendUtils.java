package life.gjq.community.utils;

import life.gjq.community.dto.EmailDTO;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSendUtils {

//    private static final Logger logger = LoggerFactory.getLogger(MailSendUtils.class);

    /**
     * 发送者地址
     **/
    @Value("${spring.mail.username}")
    private String form;

    /**
     * 发送者姓名
     **/
    private static final String posterName = "龚同学";


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;


    /**
     * 文本发送
     **/
    public void sendEmailAsText(final EmailDTO emailModel) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            mimeMessage.setFrom(form);
            mimeMessage.setRecipients(Message.RecipientType.TO, emailModel.getRecieverEmailAddress());
            mimeMessage.setText("<html><body>"
                    + "hello：" + emailModel.getRecieverName()
                    + "<br>" + "msg：" + emailModel.getEmailContent()
                    + "<br>" + "from :" + posterName
                    + "</body></html>");
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
//            logger.debug("邮箱已返送至[{}]邮箱！", emailModel.getRecieverName());
        } catch (MailException e) {
//            logger.error("邮箱异常返回：{}", e);
        }
    }

    /**
     * html 网页发送
     * 该方法为同步方法
     **/
    public void sendEmailAsSysExceptionHtml(final EmailDTO emailModel) {
        MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(emailModel.getEmailTheme());
            mimeMessageHelper.setFrom(form);
            mimeMessageHelper.setTo(emailModel.getRecieverEmailAddress());
            mimeMessageHelper.setText("<head>\n" +
                            "    <base target=\"_blank\" />\n" +
                            "    <style type=\"text/css\">::-webkit-scrollbar{ display: none; }</style>\n" +
                            "    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}</style>\n" +
                            "    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                            "    <style type=\"text/css\">\n" +
                            "        body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                            "        td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                            "        pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                            "        th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                            "        img{ border:0}\n" +
                            "        header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                            "        blockquote{margin-right:0px}\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body tabindex=\"0\" role=\"listitem\">\n" +
                            "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                            "    <tbody>\n" +
                            "    <tr>\n" +
                            "        <td>\n" +
                            "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                            "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                            "                    <tbody><tr><td width=\"210\"></td></tr></tbody>\n" +
                            "                </table>\n" +
                            "            </div>\n" +
                            "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                            "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                            "                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span style=\"color:#f60;font-size: 16px;\"></span>您好！\n" +
                            "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                            "                        您正在进行<span style=\"color: red\">注册密码</span>操作，请在验证码输入框中输入：<span style=\"color:#f60;font-size: 24px\">"+emailModel.getEmailContent()+"</span>，以完成操作。\n" +
                            "                    \n" +
                            "                </div>\n" +
                            "                <div style=\"margin-bottom:30px;\">\n" +
                            "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                            "                        <p style=\"color:#747474;\">\n" +
                            "                            注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                            "                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                            "                        </p>\n" +
                            "                    </small>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "            <div style=\"width:700px;margin:0 auto;\">\n" +
                            "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                            "                    <p>此为系统邮件，请勿回复<br>\n" +
                            "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                            "                    </p>\n" +
                            "                    <p>考研交流论坛</p>\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </td>\n" +
                            "    </tr>\n" +
                            "    </tbody>\n" +
                            "</table>\n" +
                            "</body>\n"
                    , true);

            this.javaMailSender.send(mimeMessage);
//            logger.debug("邮箱已返送至[{}]邮箱！", emailModel.getRecieverName());

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
//            logger.error("邮箱异常：{}", e);
        }
    }

}
