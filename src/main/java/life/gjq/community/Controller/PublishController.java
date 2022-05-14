package life.gjq.community.Controller;

import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.dto.QuestionDTO;
import life.gjq.community.mapper.QuestionMapper;
import life.gjq.community.model.Question;
import life.gjq.community.model.User;
import life.gjq.community.service.NotificationService;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getName());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",id);
        return "/publish";
    }



    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable(name="id") Long id,
                           HttpServletRequest request,
                           @RequestParam(value = "page",defaultValue = "1") Integer page,
                           @RequestParam(value = "size",defaultValue = "5") Integer size,
                           Model model) {
        User user = (User)request.getSession().getAttribute("user");
        Question question = questionMapper.selectByPrimaryKey(id);
        System.out.println(user.getId()+"  "+question.getCreator());
        if(user == null){
            return "redirect:/";
        }
       else if(user.getId()!=question.getCreator()){
            return "redirect:/";
        }else {
            questionMapper.deleteByPrimaryKey(id);
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            PaginationDTO pagination = questionService.listByUserId(user.getId(),page,size);
            model.addAttribute("pagination",pagination);
            return "profile";
        }
    }

//    @GetMapping("/profile.html")//处理上面发送过来的manage.html请求
//    public String managePage(){
//        return "profile";
//    }

    @GetMapping("/publish")
    public String publish( HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Long id,
            HttpServletRequest request,
            Model model
    ) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Question question = new Question();
        question.setName(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
