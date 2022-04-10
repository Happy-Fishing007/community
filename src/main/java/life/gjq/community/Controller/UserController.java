package life.gjq.community.Controller;

import life.gjq.community.model.User;
import life.gjq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user,String code){
        String redisCode= stringRedisTemplate.opsForValue().get(user.getUserId());
        if(code.equals(redisCode)){
            String tip = userService.localCreate(user);
            return tip;
        }
        return "验证码错误";
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

}
