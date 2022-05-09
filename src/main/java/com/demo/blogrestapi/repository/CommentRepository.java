package com.demo.blogrestapi.repository;

import com.demo.blogrestapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
