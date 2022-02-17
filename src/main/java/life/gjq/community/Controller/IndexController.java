package life.gjq.community.Controller;


import life.gjq.community.dto.QuestionDTO;
import life.gjq.community.mapper.QuestionMapper;
import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.Question;
import life.gjq.community.model.User;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    //    @GetMapping("/")
//    public String hello(@RequestParam(name = "name") String name, Model model) {
//"
////        model.addAttribute(name", name);
//        return "index";
//    }
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
    Model model) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null&& user.getId()!=null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
