package guat.lxy.smartshop.demos.web.mapper;

import guat.lxy.smartshop.demos.web.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 查询所有分类
    @Select("SELECT * FROM category")
    List<Category> selectAll();

    // ✅ 新增：按名称搜索分类
    @Select("SELECT * FROM category WHERE name LIKE CONCAT('%', #{keyword}, '%')")
    List<Category> searchByName(@Param("keyword") String keyword);

    // 新增分类
    @Insert("INSERT INTO category(name, descp) VALUES(#{name}, #{descp})")
    int insert(Category category);

    // 删除分类
    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteById(Integer id);

    // 根据ID查询
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Integer id);

    // 更新分类
    @Update("UPDATE category SET name = #{name}, descp = #{descp} WHERE id = #{id}")
    int update(Category category);
}