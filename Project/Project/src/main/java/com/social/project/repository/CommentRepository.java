package com.social.project.repository;

import com.social.project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // You can add custom queries here if needed
}
