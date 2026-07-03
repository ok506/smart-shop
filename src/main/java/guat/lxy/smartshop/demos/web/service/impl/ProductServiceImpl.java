package guat.lxy.smartshop.demos.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.entity.Product;
import guat.lxy.smartshop.demos.web.mapper.CategoryMapper;
import guat.lxy.smartshop.demos.web.mapper.ProductMapper;
import guat.lxy.smartshop.demos.web.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageInfo<Product> page(int pageNum, int pageSize, String keyword, Integer categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        // 如果关键词为空或null，设为空字符串
        if (keyword == null) {
            keyword = "";
        }
        // 如果分类ID为null或0，设为null（表示全部）
        if (categoryId != null && categoryId == 0) {
            categoryId = null;
        }
        List<Product> list = productMapper.search(keyword, categoryId);
        // 组装分类对象
        for (Product product : list) {
            if (product.getCatId() != null) {
                Category category = categoryMapper.selectById(product.getCatId());
                product.setCategory(category);
            }
        }
        return new PageInfo<>(list);
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void del(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public Product getById(Integer id) {
        Product product = productMapper.selectById(id);
        if (product != null && product.getCatId() != null) {
            Category category = categoryMapper.selectById(product.getCatId());
            product.setCategory(category);
        }
        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectAll();
    }
}