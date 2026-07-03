package guat.lxy.smartshop.demos.web.mapper;

import guat.lxy.smartshop.demos.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    // 关联查询角色
    @Select("SELECT u.id, u.username, u.password, r.role " +
            "FROM t_user u " +
            "LEFT JOIN t_user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN t_role r ON ur.role_id = r.id " +
            "WHERE u.username = #{username}")
    User selectByUsername(@Param("username") String username);
}