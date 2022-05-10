package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.dto.CommentDto;
import com.demo.blogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment (@PathVariable("postId") long postId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId (@PathVariable("postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostId (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);

        return new ResponseEntity<>("Comment with id : " + commentId + " Deleted", HttpStatus.OK);
    }
}
