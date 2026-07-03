package guat.lxy.smartshop.demos.web.controller;

import com.github.pagehelper.PageInfo;
import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.entity.Product;
import guat.lxy.smartshop.demos.web.service.CategoryService;
import guat.lxy.smartshop.demos.web.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private CategoryService categoryService;

    // ✅ 支持搜索和分类筛选
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer categoryId,
            Model model) {

        // 默认分页大小
        int pageSize = 5;

        // 执行分页搜索
        PageInfo<Product> page = productService.page(pageNum, pageSize, keyword, categoryId);

        // 获取所有分类（用于下拉框）
        List<Category> categoryList = categoryService.list();

        // 回显搜索条件
        model.addAttribute("page", page);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);

        return "product/list";
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("categoryList", categoryService.list());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(Product product) {
        productService.add(product);
        return "redirect:/product/list";
    }

    @GetMapping("/del")
    public String del(@RequestParam Integer id) {
        productService.del(id);
        return "redirect:/product/list";
    }

    @GetMapping("/toEdit")
    public String toEdit(@RequestParam Integer id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("item", product);
        model.addAttribute("categoryList", categoryService.list());
        return "product/add";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/product/list";
    }
}