package com.seu.studentblog.config;

import com.seu.studentblog.model.Admin;
import com.seu.studentblog.model.Blog;
import com.seu.studentblog.model.Category;
import com.seu.studentblog.repository.AdminRepository;
import com.seu.studentblog.repository.BlogRepository;
import com.seu.studentblog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Seeds sample data on first run so the app is demo-ready.
 * Safe to delete once real content/admin accounts exist.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                    new Category(null, "Programming", "💻", "Languages, patterns and development concepts."),
                    new Category(null, "Web Development", "🌐", "Frontend, backend and full-stack fundamentals."),
                    new Category(null, "Artificial Intelligence", "🤖", "Machine learning, deep learning and applied AI."),
                    new Category(null, "Networking", "📡", "Protocols, infrastructure and connectivity."),
                    new Category(null, "Database", "🗄️", "SQL, NoSQL and data modeling."),
                    new Category(null, "Career", "💼", "Internships, interviews and job prep."),
                    new Category(null, "Student Life", "🏫", "Campus life and personal growth.")
            ));
        }

        if (blogRepository.count() == 0) {
            LocalDateTime now = LocalDateTime.now();
            blogRepository.saveAll(List.of(
                new Blog(null, "How I Built My First Spring Boot Application", "first-spring-boot-app",
                    "Programming", "Rahim Ahmed", "CSE · 3rd Year",
                    "A walkthrough of my first real backend project.",
                    "<p>Full article content goes here...</p>",
                    "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=1000&q=80",
                    5, Blog.BlogStatus.PUBLISHED, now, now),
                new Blog(null, "Understanding OOP Through Real Projects", "understanding-oop-through-real-projects",
                    "Programming", "Nusrat Jahan", "CSE · 2nd Year",
                    "Inheritance and polymorphism finally made sense once I started building.",
                    "<p>Full article content goes here...</p>",
                    "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1000&q=80",
                    6, Blog.BlogStatus.PUBLISHED, now, now)
            ));
        }

        if (adminRepository.count() == 0) {
            Admin admin = new Admin(null, "admin@seublog.edu",
                    passwordEncoder.encode("ChangeMe123!"), "SEU Blog Admin", "ADMIN");
            adminRepository.save(admin);
        }
    }
}
