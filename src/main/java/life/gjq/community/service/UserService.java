package life.gjq.community.service;

import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.User;
import life.gjq.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;




    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(user.getUserId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setToken(user.getToken());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }

    public String localCreate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserIdEqualTo(user.getUserId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            //插入
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAvatarUrl("/images/default-avatar.png");
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            return "注册成功";
        }else{
            return "该用户已存在";
        }
    }

    public User login(String email, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                        .andUserIdEqualTo(email)
                                .andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        System.out.println(users.size());
    return  users.get(0);
    }
}
