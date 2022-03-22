package life.gjq.community.Controller;

import life.gjq.community.dto.CommentDTO;
import life.gjq.community.dto.QuestionDTO;
import life.gjq.community.dto.ResultDTO;
import life.gjq.community.enums.CommentTypeEnum;
import life.gjq.community.service.CommentService;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable(name="id") Long id,
                            Model model) {
        QuestionDTO questionDTO=questionService.getById(id);
        List<CommentDTO> comments=commentService.getCommentDTOListById(id, CommentTypeEnum.QUESTION);
        //累加评论
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }


    @ResponseBody
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> comments = commentService.getCommentDTOListById(id, CommentTypeEnum.QUESTION);
        return ResultDTO.okOf(comments);
     }
}
