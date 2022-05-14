package life.gjq.community.Controller;

import life.gjq.community.model.User;
import life.gjq.community.provider.AliyunProvider;
import life.gjq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private AliyunProvider aliyunProvider;
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user, String code){
        String redisCode= stringRedisTemplate.opsForValue().get(user.getUserId());
        if(code.equals(redisCode)){
            String tip = userService.localCreate(user);
            return tip;
        }
        return "验证码错误";
    }

    @ResponseBody
    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public String forget(@RequestBody User user, String code){
        String redisCode= stringRedisTemplate.opsForValue().get(user.getUserId());
        if(code.equals(redisCode)){
            String tip = userService.localUpdate(user);
            return tip;
        }else {
            return "验证码错误";
        }

    }

    @PostMapping("/login")
    public String login(@RequestParam(name="email") String email,
                        @RequestParam(name="password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response){

      User user =userService.login(email,password);
        if(user!=null){
            request.getSession().setAttribute("user", user);
            Cookie cookie = new Cookie("token", user.getToken());
            response.addCookie(cookie);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


    @GetMapping("/personal")
    public String personal(){
        return "personal";
    }

    @PostMapping("/personal")
    public String doPersonal(
            @RequestParam(value = "userId",required = false) String userId,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam("picture") MultipartFile file,
            Model model
    ){

        if(file.isEmpty()&&name==null){
            model.addAttribute("error","修改内容不能都为空");
            return "/personal";
        }

        User user = new User();
        user.setUserId(userId);
        user.setGmtModified(System.currentTimeMillis());
        if (!file.isEmpty()) {
            // 生成文件名称
            String nameSuffix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."))
                    .replaceAll(" ", "_").replaceAll(",", "")
                    + UUID.randomUUID().toString();
            //上传原始图片到阿里云
            String uploadPath = aliyunProvider.uploadFile(file, nameSuffix);

            user.setAvatarUrl(uploadPath);
            if(name!=null){user.setName(name);}

        }else{
            user.setName(name);
        }
        userService.localUpdate(user);
            return "redirect:/";
        }

}
