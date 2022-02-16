package life.gjq.community;

import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

//     User user = userMapper.findById("1");
//        System.out.println(user);
    }
}
