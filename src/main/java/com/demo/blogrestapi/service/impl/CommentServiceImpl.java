package com.demo.blogrestapi.service.impl;

import com.demo.blogrestapi.exception.BlogException;
import com.demo.blogrestapi.exception.ResourceNotFoundException;
import com.demo.blogrestapi.model.Comment;
import com.demo.blogrestapi.model.Post;
import com.demo.blogrestapi.payload.CommentDto;
import com.demo.blogrestapi.repository.CommentRepository;
import com.demo.blogrestapi.repository.PostRepository;
import com.demo.blogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    private Comment convertToEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }

    private CommentDto convertToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }

    private Post findPostById(long id) {
        return postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    private Comment findCommentById(long id) {
        return commentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    private boolean doesCommentBelongsToPost(Comment comment, Post post) {
        return !comment.getPost().getId().equals(post.getId());
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = convertToEntity(commentDto);

        Post post = findPostById(postId);

        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return convertToDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().
                map(this::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = findPostById(postId);

        Comment comment = findCommentById(commentId);

        if (doesCommentBelongsToPost(comment, post)) {
            throw new BlogException("Comment does belong to post");
        }

        return convertToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = findPostById(postId);

        Comment comment = findCommentById(commentId);

        if (doesCommentBelongsToPost(comment, post)) {
            throw new BlogException("Comment does belong to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return convertToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = findPostById(postId);

        Comment comment = findCommentById(commentId);

        if (doesCommentBelongsToPost(comment, post)) {
            throw new BlogException("Comment does belong to post");
        }

        commentRepository.delete(comment);
    }
}
