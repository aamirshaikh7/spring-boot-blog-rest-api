package com.demo.blogrestapi.service;

import com.demo.blogrestapi.dto.PostDto;
import com.demo.blogrestapi.dto.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String dir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
