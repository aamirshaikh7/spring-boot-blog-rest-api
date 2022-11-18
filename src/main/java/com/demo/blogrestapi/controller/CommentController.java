package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.payload.CommentDto;
import com.demo.blogrestapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("CRUD Rest APIs for Comment Resource")
@RestController
@RequestMapping("api/v1")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("Create Comment Rest API")
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment (@PathVariable("postId") long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Comments By Post Id Rest API")
    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId (@PathVariable("postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @ApiOperation("Get Single Comment By Post Id Rest API")
    @GetMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostId (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @ApiOperation("Update Comment Rest API")
    @PutMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @ApiOperation("Delete Comment Rest API")
    @DeleteMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment (@PathVariable("postId") long postId, @PathVariable("commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);

        return new ResponseEntity<>("Comment with id : " + commentId + " Deleted", HttpStatus.OK);
    }
}
