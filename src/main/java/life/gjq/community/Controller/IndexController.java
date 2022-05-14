package life.gjq.community.Controller;


import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.mapper.NoticeMapper;
import life.gjq.community.model.Notice;
import life.gjq.community.model.NoticeExample;
import life.gjq.community.service.NoticeService;
import life.gjq.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeMapper noticeMapper;
    @GetMapping("/")
    public String index(
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "7") Integer size,
                        @RequestParam(value = "search",required = false) String  search,
                        Model model
     ) {
       if(search==""){search=null;}
        PaginationDTO pagination = questionService.list(search,page,size);



        Notice noticeTop=noticeService.selectTopNotice();
        List<Notice> noticeBottom=noticeService.selectBottomNotice();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("noticeTop",noticeTop);
        model.addAttribute("noticeBottom",noticeBottom);

        return "index";
    }
}
