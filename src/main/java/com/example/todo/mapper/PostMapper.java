package com.example.todo.mapper;

import com.example.todo.dto.PostDto;
import com.example.todo.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    List<Post> findAllPosts();
    Optional<Post> findPostById(int id);
    void createPost(Post post);
    void updatePost(@Param("id") int id, @Param("post") PostDto postDto);
    void deletePost(int id);
}
