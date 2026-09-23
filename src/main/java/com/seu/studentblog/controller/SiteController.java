package com.seu.studentblog.controller;

import com.seu.studentblog.model.Blog;
import com.seu.studentblog.service.BlogService;
import com.seu.studentblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class SiteController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("latestBlogs",
                blogService.findPublished(PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false) String category,
                        @RequestParam(required = false) String q,
                        @RequestParam(defaultValue = "0") int page,
                        Model model) {
        var pageable = PageRequest.of(page, 9, Sort.by(Sort.Direction.DESC, "createdAt"));
        var result = (q != null && !q.isBlank())
                ? blogService.search(q, pageable)
                : (category != null && !category.equalsIgnoreCase("All")
                   ? blogService.findByCategory(category, pageable)
                   : blogService.findPublished(pageable));

        model.addAttribute("blogPage", result);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("activeCategory", category);
        model.addAttribute("query", q);
        return "blogs";
    }

    @GetMapping("/blogs/{slug}")
    public String blogDetails(@PathVariable String slug, Model model) {
        Blog blog = blogService.getBySlug(slug);
        model.addAttribute("blog", blog);
        model.addAttribute("related", blogService.relatedTo(blog));
        return "blog-details";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    // ---- Public "write a post" flow: no account/login needed ----

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "write";
    }

    @PostMapping("/write")
    public String submitPost(@ModelAttribute Blog blog, Model model) {
        Blog saved = blogService.submitByStudent(blog);
        return "redirect:/blogs/" + saved.getSlug() + "?submitted";
    }
}
