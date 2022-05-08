package com.demo.blogrestapi.service;

import com.demo.blogrestapi.dto.PostDto;
import com.demo.blogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
