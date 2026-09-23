package com.seu.studentblog.service;

import com.seu.studentblog.model.Blog;
import com.seu.studentblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private static final Pattern NON_ALNUM = Pattern.compile("[^a-z0-9]+");

    public Page<Blog> findPublished(Pageable pageable) {
        return blogRepository.findByStatus(Blog.BlogStatus.PUBLISHED, pageable);
    }

    public Page<Blog> findByCategory(String category, Pageable pageable) {
        return blogRepository.findByCategoryAndStatus(category, Blog.BlogStatus.PUBLISHED, pageable);
    }

    public Page<Blog> search(String query, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCaseAndStatus(query, Blog.BlogStatus.PUBLISHED, pageable);
    }

    public Blog getBySlug(String slug) {
        return blogRepository.findBySlug(slug)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found: " + slug));
    }

    public List<Blog> relatedTo(Blog blog) {
        return blogRepository.findTop3ByStatusAndCategoryNotAndIdNot(
                Blog.BlogStatus.PUBLISHED, blog.getCategory(), blog.getId());
    }

    // ---- Admin operations ----

    public Page<Blog> findAllForAdmin(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    public Blog getById(String id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found: " + id));
    }

    public Blog save(Blog blog) {
        LocalDateTime now = LocalDateTime.now();
        if (blog.getId() == null) {
            blog.setCreatedAt(now);
        }
        blog.setUpdatedAt(now);
        return blogRepository.save(blog);
    }

    public void delete(String id) {
        blogRepository.deleteById(id);
    }

    // ---- Public student self-publish (no account needed) ----

    /**
     * A student submits a post directly from the public site. No login is
     * required, so we auto-generate a slug, default the author to
     * "Anonymous" when left blank, and publish it immediately.
     */
    public Blog submitByStudent(Blog blog) {
        if (blog.getAuthor() == null || blog.getAuthor().isBlank()) {
            blog.setAuthor("Anonymous Student");
        }
        if (blog.getExcerpt() == null || blog.getExcerpt().isBlank()) {
            blog.setExcerpt(buildExcerpt(blog.getContent()));
        }
        if (blog.getReadingTimeMinutes() == null) {
            blog.setReadingTimeMinutes(estimateReadingTime(blog.getContent()));
        }
        blog.setSlug(generateUniqueSlug(blog.getTitle()));
        blog.setStatus(Blog.BlogStatus.PUBLISHED); // auto-publish, no admin approval step
        LocalDateTime now = LocalDateTime.now();
        blog.setCreatedAt(now);
        blog.setUpdatedAt(now);
        blog.setId(null); // always a new document, never overwrite an existing one
        return blogRepository.save(blog);
    }

    private String generateUniqueSlug(String title) {
        String base = slugify(title);
        if (base.isBlank()) {
            base = "post";
        }
        String slug = base;
        int suffix = 1;
        while (blogRepository.findBySlug(slug).isPresent()) {
            suffix++;
            slug = base + "-" + suffix;
        }
        return slug;
    }

    private String slugify(String input) {
        if (input == null) return "";
        String normalized = input.trim().toLowerCase(Locale.ROOT);
        normalized = NON_ALNUM.matcher(normalized).replaceAll("-");
        normalized = normalized.replaceAll("^-+|-+$", "");
        return normalized;
    }

    private String buildExcerpt(String content) {
        if (content == null) return "";
        String plain = content.replaceAll("<[^>]*>", " ").replaceAll("\\s+", " ").trim();
        return plain.length() > 180 ? plain.substring(0, 180) + "..." : plain;
    }

    private Integer estimateReadingTime(String content) {
        if (content == null || content.isBlank()) return 1;
        int words = content.trim().split("\\s+").length;
        return Math.max(1, (int) Math.ceil(words / 200.0));
    }
}
