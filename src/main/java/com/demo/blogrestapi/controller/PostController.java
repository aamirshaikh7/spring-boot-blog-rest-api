package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.payload.PostDto;
import com.demo.blogrestapi.payload.PostResponse;
import com.demo.blogrestapi.service.PostService;
import com.demo.blogrestapi.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("CRUD Rest APIs for Post Resource")
@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("Create Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost (@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Posts Post Rest API")
    @GetMapping
    public PostResponse getAllPosts (
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "dir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false) String dir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, dir);
    }

    @ApiOperation("Get Post By Id Rest API")
    @GetMapping(value = "{id}")
    public ResponseEntity<PostDto> getPostById (@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @ApiOperation("Update Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost (@Valid @RequestBody PostDto postDto, @PathVariable("id") long id) {
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @ApiOperation("Delete Post Rest API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById (@PathVariable("id") long id) {
        postService.deletePostById(id);

        return new ResponseEntity<>("Post with id : " + id + " Deleted", HttpStatus.OK);
    }
}
