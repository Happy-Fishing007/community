package life.gjq.community.mapper;

import life.gjq.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);


    @Insert("insert into user(user,name,token,gmt_create,gmt_modified) values(#{user},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
