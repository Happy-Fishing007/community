package life.gjq.community.Controller;

import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.User;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          HttpServletRequest request,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "2") Integer size,
                          Model model) {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(action)) {

                model.addAttribute("section","sections");
                model.addAttribute("sectionName","我的回复");
        }
        PaginationDTO pagination = questionService.listByUserId(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
