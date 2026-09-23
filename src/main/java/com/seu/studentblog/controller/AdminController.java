package com.seu.studentblog.controller;

import com.seu.studentblog.model.Blog;
import com.seu.studentblog.service.BlogService;
import com.seu.studentblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("recentBlogs",
                blogService.findAllForAdmin(PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent());
        return "admin/dashboard";
    }

    @GetMapping("/blogs")
    public String blogList(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("blogPage",
                blogService.findAllForAdmin(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"))));
        return "admin/blogs";
    }

    @GetMapping("/blogs/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/blog-form";
    }

    @GetMapping("/blogs/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("blog", blogService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "admin/blog-form";
    }

    @PostMapping("/blogs")
    public String saveBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable String id) {
        blogService.delete(id);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories";
    }
}
