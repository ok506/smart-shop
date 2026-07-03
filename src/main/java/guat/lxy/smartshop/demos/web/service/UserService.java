package guat.lxy.smartshop.demos.web.service;


import guat.lxy.smartshop.demos.web.entity.User;

public interface UserService {
    User findUserByUsername(String username);
}

