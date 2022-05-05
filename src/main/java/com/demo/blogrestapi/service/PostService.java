package com.demo.blogrestapi.service;

import com.demo.blogrestapi.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
