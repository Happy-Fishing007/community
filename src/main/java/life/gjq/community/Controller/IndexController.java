package life.gjq.community.Controller;


import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.mapper.UserMapper;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size,
                        @RequestParam(value = "search",required = false) String  search,
                        Model model
     ) {

        PaginationDTO pagination = questionService.list(search,page,size);
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);

        return "index";
    }
}
