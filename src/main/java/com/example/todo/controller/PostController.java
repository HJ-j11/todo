package com.example.todo.controller;


import com.example.todo.dto.PostDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.exception.RestApiException;
import com.example.todo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation( summary = "Post List 조회", description = "Post 전체를 조회한다.")
    @GetMapping("/posts")
    public ApiResponse<List<PostDto>> findAllPosts(@PageableDefault(size=5) Pageable pageable) {
        List<PostDto> posts = postService.findAllPost(pageable);
        return ApiResponse.success(posts);
    }

    @Operation( summary = "Post 단건 조회")
    @GetMapping("/posts/{id}")
    public ApiResponse<PostDto> findPostById(@PathVariable int id) {
        PostDto post = postService.getPostById(id);
        if(post == null)
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        return ApiResponse.success(post);
    }

    @Operation( summary = "Post 생성")
    @PostMapping("/posts")
    public ApiResponse<Void> savePost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return ApiResponse.successCreated();
    }

    @Operation( summary = "Post 수정")
    @PutMapping("/posts/{id}")
    public ApiResponse<Void> updatePost(@PathVariable int id, @RequestBody PostDto postDto) {
        postService.updatePost(id, postDto);
        return ApiResponse.successWithoutContent();
    }

    @Operation( summary = "Post 삭제")
    @DeleteMapping("/posts/{id}")
    public ApiResponse<Void> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ApiResponse.successWithoutContent();
    }

}
