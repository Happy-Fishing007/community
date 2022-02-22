package life.gjq.community.mapper;

import life.gjq.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Insert("insert into question(creator,title,description,get_create,get_modified,tag) values(#{creator},#{title},#{description},#{getCreate},#{getModified},#{tag})")
    void  create (Question question);

    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId")Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(Integer userId);
    @Select("select * from question where id = #{id} ")
    Question getById(@Param("id")Integer id);
    @Update("update question set title=#{title},description=#{description},get_modified=#{getModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}
