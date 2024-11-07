package com.example.todo;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoApplicationTest {
    private static final int userId = 2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("글 작성")
    @Test
    public void createPostTest() throws Exception {
        // Given
        Event event = Event.builder()
                .id(10L)
                .title("test title")
                .location("test content")
                .createdAt(LocalDateTime.of(2024, 5, 13, 16, 30))
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(event))
        );

        //Then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("작성 글 조회")
    @Test
    public void getPostById() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/posts/"+userId)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("TITLE2"))
                .andExpect(jsonPath("$.content").value("CONTENT2"));
    }

    @DisplayName("글 목록 갯수 확인")
    @Test
    public void countPosts() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<EventDto> posts =  objectMapper.readValue(responseBody, List.class);
        assertEquals(posts.size(), 14);
    }
}