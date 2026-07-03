package guat.lxy.smartshop.demos.web.mapper;

import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    // ✅ 支持多条件动态查询（商品名称模糊搜索 + 分类筛选）
    @Select("<script>" +
            "SELECT p.*, c.name AS category_name " +
            "FROM product p " +
            "LEFT JOIN category c ON p.cat_id = c.id " +
            "<where> " +
            "   <if test='keyword != null and keyword != \"\"'> " +
            "       AND p.name LIKE CONCAT('%', #{keyword}, '%') " +
            "   </if> " +
            "   <if test='categoryId != null and categoryId != 0'> " +
            "       AND p.cat_id = #{categoryId} " +
            "   </if> " +
            "</where> " +
            "ORDER BY p.id DESC" +
            "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "catId", column = "cat_id"),
            @Result(property = "img", column = "photo_url"),
            @Result(property = "descp", column = "descp"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "category", column = "cat_id", javaType = Category.class,
                    one = @One(select = "guat.lxy.smartshop.demos.web.mapper.CategoryMapper.selectById"))
    })
    List<Product> search(@Param("keyword") String keyword, @Param("categoryId") Integer categoryId);

    // 原有的查询方法（保留）
    @Select("SELECT p.*, c.name AS category_name FROM product p LEFT JOIN category c ON p.cat_id = c.id ORDER BY p.id DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "catId", column = "cat_id"),
            @Result(property = "img", column = "photo_url"),
            @Result(property = "descp", column = "descp"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "category", column = "cat_id", javaType = Category.class,
                    one = @One(select = "guat.lxy.smartshop.demos.web.mapper.CategoryMapper.selectById"))
    })
    List<Product> selectAll();

    @Insert("INSERT INTO product(name, price, cat_id, photo_url, descp, release_date) VALUES(#{name}, #{price}, #{catId}, #{img}, #{descp}, #{releaseDate})")
    int insert(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteById(Integer id);

    @Select("SELECT p.*, c.name AS category_name FROM product p LEFT JOIN category c ON p.cat_id = c.id WHERE p.id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "catId", column = "cat_id"),
            @Result(property = "img", column = "photo_url"),
            @Result(property = "descp", column = "descp"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "category", column = "cat_id", javaType = Category.class,
                    one = @One(select = "guat.lxy.smartshop.demos.web.mapper.CategoryMapper.selectById"))
    })
    Product selectById(Integer id);

    @Update("UPDATE product SET name = #{name}, price = #{price}, cat_id = #{catId}, photo_url = #{img}, descp = #{descp}, release_date = #{releaseDate} WHERE id = #{id}")
    int update(Product product);
}