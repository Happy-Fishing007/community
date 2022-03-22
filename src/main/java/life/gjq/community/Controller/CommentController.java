package life.gjq.community.Controller;


import life.gjq.community.dto.CommentCreatDTO;
import life.gjq.community.dto.CommentDTO;
import life.gjq.community.dto.LikeCountDTO;
import life.gjq.community.dto.ResultDTO;
import life.gjq.community.enums.CommentTypeEnum;
import life.gjq.community.exception.CustomizeErrorCode;
import life.gjq.community.mapper.CommentMapper;
import life.gjq.community.model.Comment;
import life.gjq.community.model.User;
import life.gjq.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreatDTO commentCreatDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreatDTO == null || commentCreatDTO.getContent() == null || commentCreatDTO.getContent().equals("")) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreatDTO.getParentId());
        comment.setComment(commentCreatDTO.getContent());
        comment.setType(commentCreatDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOS = commentService.getCommentDTOListById(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

    @ResponseBody
    @RequestMapping(value = "/likeCount", method = RequestMethod.POST)
    public ResultDTO<Comment> likeCount(@RequestBody LikeCountDTO likeCountDTO) {

        Comment comment = new Comment();
        comment.setId(likeCountDTO.getId());
        comment.setLikeCount(likeCountDTO.getLikeCount());

        commentService.incLikeCount(comment);
        Comment commentDTO = commentService.getComment(likeCountDTO.getId());
        return ResultDTO.okOf(commentDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/level1/{id}", method = RequestMethod.GET)
    public  ResultDTO<List<CommentDTO>> likeCount(@PathVariable(name="id") Long id){
        List<CommentDTO> comments=commentService.getCommentDTOListById(id, CommentTypeEnum.QUESTION);
        return ResultDTO.okOf(comments);
    }

    @ResponseBody
    @RequestMapping(value = "/level2/{id}", method = RequestMethod.GET)
    public  ResultDTO<Map<Integer,List<CommentDTO>>>  scomment(@PathVariable(name = "id") Long id) {
        Map< Integer, List<CommentDTO>>  comments=commentService.getSecondAll(id, CommentTypeEnum.QUESTION);
        return ResultDTO.okOf(comments);
    }
}
