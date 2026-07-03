package guat.lxy.smartshop.demos.web.service;

import com.github.pagehelper.PageInfo;
import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.entity.Product;

import java.util.List;

public interface ProductService {
    // ✅ 支持搜索和分类筛选的分页查询
    PageInfo<Product> page(int pageNum, int pageSize, String keyword, Integer categoryId);

    void add(Product product);

    void del(Integer id);

    Product getById(Integer id);

    void update(Product product);

    List<Category> getAllCategories();  // 获取所有分类
}