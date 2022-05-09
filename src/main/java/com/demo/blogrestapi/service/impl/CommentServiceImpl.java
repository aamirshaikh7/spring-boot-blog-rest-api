package com.demo.blogrestapi.service.impl;

import com.demo.blogrestapi.dto.CommentDto;
import com.demo.blogrestapi.exception.ResourceNotFoundException;
import com.demo.blogrestapi.model.Comment;
import com.demo.blogrestapi.model.Post;
import com.demo.blogrestapi.repository.CommentRepository;
import com.demo.blogrestapi.repository.PostRepository;
import com.demo.blogrestapi.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    private Comment convertToEntity (CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }

    private CommentDto convertToDto (Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Post findPostById (long id) {
        return postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = convertToEntity(commentDto);

        Post post = findPostById(postId);

        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return convertToDto(savedComment);
    }
}
