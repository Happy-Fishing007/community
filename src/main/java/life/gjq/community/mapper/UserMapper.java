package life.gjq.community.mapper;

import life.gjq.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {



    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") String id);
    @Insert("insert into user(user_id,name,token,gmt_create,gmt_modified,avatar_url) values(#{userId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where user_id = #{userId}")
    User getByUserId(@Param("userId")String userId);
    @Update("update user set name = #{name},token = #{token} ,gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
