package guat.lxy.smartshop.demos.web.controller;

import guat.lxy.smartshop.demos.web.entity.Category;
import guat.lxy.smartshop.demos.web.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    // ✅ 支持搜索
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Category> list;
        if (keyword != null && !keyword.trim().isEmpty()) {
            list = categoryService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            list = categoryService.list();
        }
        model.addAttribute("list", list);
        return "category/list";
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "category/add";
    }

    @PostMapping("/add")
    public String add(Category category) {
        categoryService.add(category);
        return "redirect:/category/list";
    }

    @GetMapping("/del")
    public String del(@RequestParam Integer id) {
        categoryService.del(id);
        return "redirect:/category/list";
    }

    @GetMapping("/toEdit")
    public String toEdit(@RequestParam Integer id, Model model) {
        model.addAttribute("item", categoryService.getById(id));
        return "category/add";
    }

    @PostMapping("/update")
    public String update(Category category) {
        categoryService.update(category);
        return "redirect:/category/list";
    }
}