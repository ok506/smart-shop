package guat.lxy.smartshop.demos.web.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer active;
    private String role;  // 角色字段
}