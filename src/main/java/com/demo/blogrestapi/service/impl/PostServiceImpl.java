package com.demo.blogrestapi.service.impl;

import com.demo.blogrestapi.dto.PostDto;
import com.demo.blogrestapi.exception.ResourceNotFoundException;
import com.demo.blogrestapi.model.Post;
import com.demo.blogrestapi.repository.PostRepository;
import com.demo.blogrestapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private Post convertToEntity (PostDto postDto) {
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

    private PostDto convertToDto (Post post) {
        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);

        Post savedPost = postRepository.save(post);

        return convertToDto(savedPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().
                map(post -> convertToDto(post)).
                collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return convertToDto(post);
    }
}
