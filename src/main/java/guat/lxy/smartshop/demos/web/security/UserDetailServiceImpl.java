package guat.lxy.smartshop.demos.web.security;

import guat.lxy.smartshop.demos.web.entity.User;
import guat.lxy.smartshop.demos.web.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取角色，如果为空则设为 normal
        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            role = "normal";
        }

        // 构建权限
        List<GrantedAuthority> auth = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role)
        );

        // ✅ 去掉 {noop} 前缀，让用户直接输入 123456 即可登录
        String password = user.getPassword();
        if (password != null && password.startsWith("{noop}")) {
            password = password.substring(6);
        }

        System.out.println("✅ 用户登录: " + username + ", 角色: " + role);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                password,
                auth
        );
    }
}