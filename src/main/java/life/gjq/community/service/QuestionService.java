package life.gjq.community.service;

import life.gjq.community.dto.PaginationDTO;
import life.gjq.community.dto.QuestionDTO;
import life.gjq.community.mapper.QuestionMapper;
import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.Question;
import life.gjq.community.model.QuestionExample;
import life.gjq.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
@Autowired
    QuestionMapper questionMapper;
@Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount =(int) questionMapper.countByExample(new QuestionExample());
        paginationDTO.setPageNation(totalCount,page,size);
        if(page<1){
            page=1;
        }
//       else if(page > paginationDTO.getTotalPage()){
//            page=paginationDTO.getTotalPage();
//        }

        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
         User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question对象复制给questionDTO对象
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);
        
        //1、算出总页数，2、page越界验证
        paginationDTO.setPageNation(totalCount,page,size);
        Integer rightPage=paginationDTO.getPage();
        Integer offset=size*(rightPage-1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question对象复制给questionDTO对象
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
    Question question=questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGetCreate(System.currentTimeMillis());
            question.setGetModified(question.getGetCreate());
            questionMapper.insert(question);
        }else {
            Question questionUpdate = new Question();
            questionUpdate.setTitle(question.getTitle());
            questionUpdate.setDescription(question.getDescription());
            questionUpdate.setGetModified(System.currentTimeMillis());
            questionUpdate.setTag(question.getTag());

            QuestionExample example = new QuestionExample();
            example.createCriteria()
                            .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(questionUpdate, example);
        }
    }
}
