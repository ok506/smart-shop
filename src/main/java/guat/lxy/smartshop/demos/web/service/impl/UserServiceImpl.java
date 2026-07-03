package guat.lxy.smartshop.demos.web.service.impl;

import guat.lxy.smartshop.demos.web.entity.User;
import guat.lxy.smartshop.demos.web.mapper.UserMapper;
import guat.lxy.smartshop.demos.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        log.info("查询用户: username={}, user={}, role={}", username, user, user != null ? user.getRole() : "null");
        return user;
    }
}