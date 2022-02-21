package life.gjq.community.service;

import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public  void createOrUpdate(User modelUser) {
        User user=userMapper.getByUserId(modelUser.getUserId());
    if(user==null){
        //插入
        modelUser.setGmtCreate(System.currentTimeMillis());
        modelUser.setGmtModified(modelUser.getGmtCreate());
        userMapper.insert(modelUser);
    }else{
        //更新
        user.setToken(modelUser.getToken());
        user.setAvatarUrl(modelUser.getAvatarUrl());
        user.setGmtModified(System.currentTimeMillis());
        user.setName(modelUser.getName());
        userMapper.update(user);
    }
    }
}
