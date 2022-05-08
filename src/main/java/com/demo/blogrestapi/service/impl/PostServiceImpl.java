package com.demo.blogrestapi.service.impl;

import com.demo.blogrestapi.dto.PostDto;
import com.demo.blogrestapi.dto.PostResponse;
import com.demo.blogrestapi.exception.ResourceNotFoundException;
import com.demo.blogrestapi.model.Post;
import com.demo.blogrestapi.repository.PostRepository;
import com.demo.blogrestapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private Post findPostById (long id) {
        return postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);

        Post savedPost = postRepository.save(post);

        return convertToDto(savedPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream().
                map(post -> convertToDto(post)).
                collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = findPostById(id);

        return convertToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = findPostById(id);

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return convertToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = findPostById(id);

        postRepository.delete(post);
    }
}
