package life.gjq.community.Controller;

import life.gjq.community.mapper.FeedbackMapper;
import life.gjq.community.model.Feedback;
import life.gjq.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FeedbackController {


    @Autowired
    private FeedbackMapper feedbackMapper;
    @GetMapping("/feedback")
    public String feedback(){
        return "feedback";
    }



    @PostMapping("/feedback")
    public String doFeedback(
            @RequestParam(value = "contact",required = false) String contact,
            @RequestParam(value = "feedback",required = false) String feedback,
            HttpServletRequest request,
            Model model
    ){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "feedback";
        }
        if (contact == null || contact.equals("")) {
            model.addAttribute("error", "联系方式不能为空");
            return "feedback";
        }
        if (feedback == null || feedback.equals("")) {
            model.addAttribute("error", "反馈内容不能为空");
            return "feedback";
        }

        Feedback fb=new Feedback();
        fb.setName(user.getName());
        fb.setContact(contact);
        fb.setContent(feedback);
        fb.setCreateDate(System.currentTimeMillis());
        fb.setHandle("否");
        feedbackMapper.insert(fb);
        return "redirect:/";
    }

}
