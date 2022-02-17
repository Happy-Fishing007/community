package life.gjq.community.mapper;

import life.gjq.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    List<Question> list();

    @Insert("insert into question(creator,title,description,get_create,get_modified,tag) values(#{creator},#{title},#{description},#{getCreate},#{getModified},#{tag})")
    void  create (Question question);
}
