package guat.lxy.smartshop.demos.web.service;

import guat.lxy.smartshop.demos.web.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    List<Category> search(String keyword);  // ✅ 新增搜索

    void add(Category category);

    void del(Integer id);

    Category getById(Integer id);

    void update(Category category);
}