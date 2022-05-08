package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.dto.PostDto;
import com.demo.blogrestapi.dto.PostResponse;
import com.demo.blogrestapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost (@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById (@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost (@RequestBody PostDto postDto, @PathVariable("id") long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById (@PathVariable("id") long id) {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post with id : " + id + " Deleted", HttpStatus.OK);
    }
}
