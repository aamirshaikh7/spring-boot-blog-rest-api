package com.demo.blogrestapi.service;

import com.demo.blogrestapi.dto.CommentDto;
import com.demo.blogrestapi.dto.PostDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteCommentById(long postId, long commentId);
}
