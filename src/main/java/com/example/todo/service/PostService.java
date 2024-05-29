package com.example.todo.service;

import com.example.todo.dto.PostDto;
import com.example.todo.entity.Post;
import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.exception.RestApiException;
import com.example.todo.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;
    private final ModelMapper modelMapper;

    public List<PostDto> findAllPost(Pageable pageable) {
        List<Post> posts = postMapper.findAllPosts();
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    public PostDto getPostById(int id) {
        Optional<Post> post = postMapper.findPostById(id);
        return modelMapper.map(post, PostDto.class);
    }

    public void createPost(PostDto postDto) {
        postMapper.createPost(modelMapper.map(postDto, Post.class));
    }

    public void updatePost(int id, PostDto postDto) {
        postMapper.updatePost(id, postDto);
    }

    public void deletePost(int id) {
        postMapper.deletePost(id);
    }
}
