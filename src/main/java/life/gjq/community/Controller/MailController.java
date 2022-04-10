package life.gjq.community.Controller;


import life.gjq.community.dto.EmailDTO;
import life.gjq.community.utils.MailSendUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class MailController {

    @Autowired
    private MailSendUtils mailSendUtils;
    //这个是针对字符串操作的对象，可以直接使用，编码格式没问题
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public Object send(String to) {
        EmailDTO emailModel = new EmailDTO();
        String result = "";
        try {
            // 获取六位随机数
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                result += random.nextInt(10);
            }

            emailModel.setEmailTheme("考研交流平台验证");//邮箱主体
            emailModel.setRecieverName(to);//收件人姓名
            emailModel.setEmailContent( result);//邮件内容
            emailModel.setRecieverEmailAddress(to);//邮箱地址

            //发送邮件
            mailSendUtils.sendEmailAsSysExceptionHtml(emailModel);
        } catch (Exception e) {
            System.out.println("邮箱发送失败.");
        }
        //往redis存放一个key和value的字符串、五分钟后过期
        stringRedisTemplate.opsForValue().set(to, result, 60*5, TimeUnit.SECONDS);
        return  DigestUtils.md5Hex(result);
    }
}
