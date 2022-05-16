package com.demo.blogrestapi.controller;

import com.demo.blogrestapi.payload.PostDto;
import com.demo.blogrestapi.payload.PostDtoV2;
import com.demo.blogrestapi.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostControllerV2 {
    private PostService postService;

    public PostControllerV2(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "{id}", produces = "application/vnd.demo.v2+json")
    public ResponseEntity<PostDtoV2> getPostByIdV2 (@PathVariable("id") long id) {
        PostDto postDto = postService.getPostById(id);

        PostDtoV2 postDtoV2 = new PostDtoV2();

        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());

        List<String> tags = new ArrayList<>();

        tags.add("Spring boot");
        tags.add("JWT");

        postDtoV2.setTags(tags);

        return ResponseEntity.ok(postDtoV2);
    }
}
