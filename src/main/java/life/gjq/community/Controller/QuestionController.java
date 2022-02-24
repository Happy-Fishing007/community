package life.gjq.community.Controller;

import life.gjq.community.dto.QuestionDTO;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name="id") Integer id,
                            Model model) {
        QuestionDTO questionDTO=questionService.getById(id);
        //累加评论
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
