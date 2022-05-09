package com.demo.blogrestapi.service;

import com.demo.blogrestapi.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
