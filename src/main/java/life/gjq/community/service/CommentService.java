package life.gjq.community.service;

import life.gjq.community.dto.CommentDTO;
import life.gjq.community.enums.CommentTypeEnum;
import life.gjq.community.exception.CustomizeErrorCode;
import life.gjq.community.exception.CustomizeException;
import life.gjq.community.mapper.*;
import life.gjq.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private UserMapper userMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
            }
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }{
                commentMapper.insert(comment);
                question.setCommentCount(1);
                questionExtMapper.incCommentCount(question);
            }
        }
    }

    public Map<Integer, List<CommentDTO>> getSecondAll(Long id,CommentTypeEnum type) {
        if(type.getType()==1) {
            Question question = questionMapper.selectByPrimaryKey(id);
            if (question.getCommentCount() == 0) {
                return null;
            }
        }
        //拿到所有parentId下面的回复
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        List<Comment> comments = commentMapper.selectByExample(example);
        ArrayList<Long>  ids = new ArrayList<>();

        for (Comment comment : comments) {
            ids.add(comment.getId());
        }
        HashMap<Integer, List<CommentDTO>> integerListHashMap = new HashMap<>();

        Integer i=0;
        for (Long cid : ids) {
            List<CommentDTO> commentDTOListById =getCommentDTOListById(cid, CommentTypeEnum.COMMENT);
            integerListHashMap.put(i,commentDTOListById);
            i++;
        }
        return integerListHashMap;
    }

    public List<CommentDTO> getCommentDTOListById(Long id, CommentTypeEnum type) {
        //查看点赞数是否为0，不用下面的comments判断，是因为有点bug,且不会解决
        if(type.getType()==1){
            Question question = questionMapper.selectByPrimaryKey(id);
            if(question.getCommentCount()==0){
                return  new ArrayList<>();
            }
        }else{
            CommentExample example = new CommentExample();
            example.createCriteria()
                            .andParentIdEqualTo(id)
                                    .andTypeEqualTo(type.getType());
            List<Comment> comments = commentMapper.selectByExample(example);
            if (comments.size()==0){
                return  new ArrayList<>();
            }
        }

        //拿到所有parentId下面的回复
        CommentExample example = new CommentExample();
        example.createCriteria()
                        .andParentIdEqualTo(id)
                                .andTypeEqualTo(type.getType());
        List<Comment> comments = commentMapper.selectByExample(example);

        //拿到所有的评论者id的结果集(所有评论者)
        //使用set集合，消除重复的评论者，避免重复查询
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //将set集合内容存到list对象中
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                        .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
      //  System.out.println(users.get(0).getId());
        //将list集合存到Map中
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //两个集合的键是相同的，通过comment的键值去userMap集合中取到对应的值
        List<CommentDTO> collectDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return collectDTOS;
    }



    public void incLikeCount(Comment comment) {
        commentExtMapper.incLikeCount(comment);

 }

    public Comment getComment(long id) {
   Comment comment = commentMapper.selectByPrimaryKey(id);
   return  comment;
    }


}
