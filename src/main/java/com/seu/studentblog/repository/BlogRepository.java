package com.seu.studentblog.repository;

import com.seu.studentblog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    Optional<Blog> findBySlug(String slug);

    Page<Blog> findByStatus(Blog.BlogStatus status, Pageable pageable);

    Page<Blog> findByCategoryAndStatus(String category, Blog.BlogStatus status, Pageable pageable);

    List<Blog> findTop3ByStatusAndCategoryNotAndIdNot(Blog.BlogStatus status, String category, String id);

    Page<Blog> findByTitleContainingIgnoreCaseAndStatus(String title, Blog.BlogStatus status, Pageable pageable);
}
