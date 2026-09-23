package com.seu.studentblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "blogs")
public class Blog {

    @Id
    private String id;

    private String title;

    @Indexed(unique = true)
    private String slug;

    private String category;      // Category name, or reference categoryId if you prefer normalized data

    private String author;        // Author display name
    private String authorDept;    // e.g. "CSE · 3rd Year"

    private String excerpt;
    private String content;       // Rich text / HTML body

    private String featuredImageUrl;

    private Integer readingTimeMinutes;

    private BlogStatus status;    // DRAFT or PUBLISHED

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum BlogStatus {
        DRAFT, PUBLISHED
    }
}
