package guat.lxy.smartshop.demos.web.service.impl;

import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.mapper.CategoryMapper;
import guat.lxy.smartshop.demos.web.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.selectAll();
    }

    // ✅ 新增搜索
    @Override
    public List<Category> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return categoryMapper.selectAll();
        }
        return categoryMapper.searchByName(keyword.trim());
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void del(Integer id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }
}