package life.gjq.community.mapper;

import life.gjq.community.model.Question;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);

}